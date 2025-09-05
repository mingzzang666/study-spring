package com.example.practice.service;

import com.example.practice.domain.PostVO;
import com.example.practice.dto.PostCriteriaDTO;
import com.example.practice.dto.PostDTO;
import com.example.practice.util.Search;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public interface PostService {
    public void write(PostDTO postDTO, List<MultipartFile> files);

    public PostCriteriaDTO getList(int page, Search search);

    public Optional<PostDTO> getPost(Long id);

    public void update(PostDTO postDTO, List<MultipartFile> files, Long[] deleteFileIds);

    public void delete(Long id);

    default PostVO toPostVO(PostDTO postDTO) {
        return PostVO.builder()
                .id(postDTO.getId())
                .postTitle(postDTO.getPostTitle())
                .postContent(postDTO.getPostContent())
                .postStatus(postDTO.getPostStatus())
                .createdDatetiem(PostDTO.getCreatedDatetime())
                .memberId(postDTO.getMemberId())
                .updatedDatetime(postDTO.getUpdatedDatetime())
                .build();
    }

    default String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
