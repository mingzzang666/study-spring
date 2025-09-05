package com.example.practice.repository;

import com.example.practice.domain.PostFileVO;
import com.example.practice.dto.FileDTO;
import com.example.practice.mapper.PostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostFileDAO {
    private final PostFileMapper postFileMapper;

    public void save(PostFileVO postFileVO) { postFileMapper.insertPostFile(postFileVO); }

    public List<FileDTO> findPostFilesByPostId(Long postId) { return postFileMapper.selectPostFilesByPostId(postId); }

    public void delete(Long postId) { postFileMapper.deletePostFile(postId); }

    public void deleteById(Long id) { postFileMapper.deletePostFileById(id); }





}
