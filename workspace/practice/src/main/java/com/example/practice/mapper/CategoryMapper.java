package com.example.practice.mapper;

import com.example.practice.domain.CategoryVO;
import com.example.practice.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public List<CategoryDTO> selectAll();

    public List<CategoryDTO> selectPostId(Long id);
}
