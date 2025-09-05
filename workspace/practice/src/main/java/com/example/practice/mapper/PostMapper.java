package com.example.practice.mapper;

import com.example.practice.domain.PostVO;
import com.example.practice.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.MergedAnnotations;

import java.util.Optional;

@Mapper
public interface PostMapper {
//  추가
    public void insertPost(PostDTO postDTO);
//  게시글 단일조회
    public Optional<PostDTO> selectPost(Long id);
//  조회수 증가
    public void updatePostReadCount (Long id);
//  게시글 목록
    public List<PostDTO> selectAll(@Param("criteria")Criteria criteria, @Param("search")Search search);
//  전체 개수
    public int selectCountAll(@Param("search") Search search);
//  게시글 삭제
    public void deletePost(Long id);
//  게시글 업데이트
    public void updatePost(PostVO postVO);
}
