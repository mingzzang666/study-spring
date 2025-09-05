package com.example.practice.repository;

import com.example.practice.domain.PostVO;
import com.example.practice.dto.PostDTO;
import com.example.practice.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final PostMapper postMapper;

//  추가
    public void save(PostVO postVO) { PostMapper.insertPost(postDTO); }
//  목록
    public List<PostDTO> findAll(Criteria criteria, Search search) { return postMapper.selectAll(criteria, search); }
//  전체 개수 조회
    public int findCountAll(Search search) { return postMapper.selectCountAll(search); }
//  단일 조회
    public Optional<PostDTO> findById(Long id) { return postMapper.selectPost(id); }
//  조회수 증가
    public void updatePostReadCount(Long id) { postMapper.updatePostReadCount(id); }
//  수정
    public void setPost (PostVO postVO) { postMapper.updatePost(postVO); }
//  삭제
    public void delete (Long id) { postMapper.deletePost(id); }

}
