package com.example.practice.service;

import com.example.practice.dto.FileDTO;
import com.example.practice.dto.PostCriteriaDTO;
import com.example.practice.dto.PostDTO;
import com.example.practice.dto.PostFileDTO;
import com.example.practice.repository.CategoryDAO;
import com.example.practice.repository.FileDAO;
import com.example.practice.repository.PostDAO;
import com.example.practice.repository.PostFileDAO;
import com.example.practice.util.Criteria;
import com.example.practice.util.DateUtils;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final FileDAO fileDAO;
    private final PostFileDAO postFileDAO;
    private final CategoryDAO categoryDAO;


    @Override
    public void write(PostDTO postDTO, List<MultipartFile> files) {
        String todayPath = getPath();
        String rootPath = "C:/file/" + todayPath;

        postDAO.save(postDTO);

        files.forEach(file -> {
            if (file.getOriginalFilename().equals("")) {
                return;
            }

            UUID uuid = UUID.randomUUID();

            FileDTO fileDTO = new FileDTO();
            PostFileDTO postFileDTO = new PostFileDTO();

            fileDTO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDTO.setFileContentType(file.getContentType());

            fileDAO.save(fileDTO);

            postFileDTO.setId(fileDTO.getId());
            postFileDTO.setPostId(postDTO.getId());

            postFileDAO.save(toPostFileVO(postFileDTO));

            File directory = new File(rootPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {
                file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));

                if (file.getContentType().startsWith("image")) {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));
                    Thumbnailator.createThumbnail(file.getInputStream(), out, 100, 100);
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public PostCriteriaDTO getList(int page, Search search) {
        PostCriteriaDTO postCriteriaDTO = new PostCriteriaDTO();
        Criteria criteria = new Criteria(page, postDAO.findCountAll(search));
        List<PostDTO> posts = postDAO.findAll(criteria, search);
        posts.forEach((post -> {
            String[] arData = null;

            Object[] categories = categoryDAO.findPostId(post.getId())
                    .stream().map((category -> category.getCategories())).toArray();
            arData = new String[categories.length];

            for (int i = 0; i < categories.length; i++) {
                arData[i] = String.valueOf(categories[i]);
            }

            post.setRelativeDate(DataUtils.toRelativeTime(post.getCreatedDatetime()));
            post.setCategories(arData);
        });
        postCriteriaDTO.setCategories(categoryDAO.findAll().stream().map(categoryDTO -> categoryDTO.getCategories()).collect(Collectors.toList()));

        criteria.setHasMore(posts.size() > criteria.getRowCount());

        if(criteria.isHasMore()){
            posts.remove(posts.size() - 1);
        }

        postCriteriaDTO.setPosts(posts);
        postCriteriaDTO.setCriteria(criteria);
        return postCriteriaDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Optional<PostDTO> getPost(Long id) {
        postDAO.updatePostReadCount(id);
        Optional<PostDTO> foundPost = postDAO.findById(id);
        foundPost.ifPresent((post) -> {
            post.setCreatedDate(DateUtils.getCreatedDate(post.getId()));
        });
        return foundPost;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(PostDTO postDTO, List<MultipartFile> files, Long[] deleteFileIds) {
        String todayPath = getPath();
        String rootPath = "C:/file/" + todayPath;
        List<FileDTO> postFiles = new ArrayList<>();

        postDAO.setPost(toPostVO(postDTO));

        Arrays.stream(deleteFileIds).forEach((id) -> {
            postFiles.add(fileDAO.findById(id).orElseThrow(PostFileNotFoundException::new));
        });

        Arrays.stream(deleteFileIds).forEach((postFileDAO::deleteById));
        Arrays.stream(deleteFileIds).forEach(fileDAO::delete);

        postFiles.forEach((postFile) -> {
            File file = new File("C:/file/" + postFile.getFilePath(), postFile.getFileName());
            if(file.exists()){
                file.delete();
            }

            if(postFile.getFileContentType().startsWith("image")){
                file = new File("C:/file/" + postFile.getFilePath(), "t_" + postFile.getFileName());
                if(file.exists()){
                    file.delete();
                }
            }
        });

        files.forEach(file -> {
        if(file.getOriginalFilename().equals("")) {
            return;
        }

        UUID uuid = UUID.randomUUID();

        FileDTO fileDTO = new FileDTO();
        PostFileDTO postFileDTO = new PostFileDTO();

        fileDTO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
        fileDTO.setFileOriginalName(file.getOriginalFilename());
        fileDTO.setFilePath(todatPath);
        fileDTO.setFileSize(String.valueOf(file.getSize()));
        fileDTO.setFileContentType(file.getContentType());

        fileDAO.save(fileDTO);

        postFileDTO.setId(fileDTO.getId());
        postFileDTO.setPostId(postDTO.getId());

        postFileDAO.save(toPostFileVO(postFileDTO));

        File directory = new File(rootPath);
        if(!directory.exists()){
            directory.mkdirs();}
        }

        try {

            file.transferTo(new File(rootPath, uuid.toString() + "_") + file.getOriginalFilename()));

            if(file.getContentType().startsWith("image")) {

                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid.toString() + "_" + file.getOriginalFilename()));
                Thumbnailator.createThumnail(file.getInputStream(), out, 100, 100);
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        postDAO.delete(id);
        List<FileDTO> postFiles = postFileDAO.findPostFilesByPostId(id);

        postFileDAO.delete(id);
        postFiles.stream().map(postFile -> postFile.getId()).forEach(fileDAO::delete);

        postFiles.forEach((postFile) -> {
            File file = new File("C:/file/" + postFile.getFilePath(), postFile.getFileName());
            if(file.exists()){
                file.delete();
            }

            if(postFile.getFileContentType().startsWith("image")) {
                file = new File("C:/file/" + postFile.getFilePath(), "t_" + postFile.getFileName());
                if(file.exists()){
                    file.delete();
                }
            }
        });
    }
}
