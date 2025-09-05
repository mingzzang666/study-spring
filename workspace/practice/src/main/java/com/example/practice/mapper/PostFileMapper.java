package com.example.practice.mapper;

import com.example.practice.domain.PostFileVO;
import com.example.practice.dto.FileDTO;
import com.example.practice.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostFileMapper {

    public void insertPostFile(PostFileVO postFileVO);

    public List<FileDTO> selectPostFilesByPostId(Long postId);

    public void deletePostFile(Long postId);
    public void deletePostFileById(Long id);

}
