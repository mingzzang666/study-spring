package com.example.practice.dto;

import com.example.practice.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ToString
public class PostCriteriaDTO {
    private List<PostDTO> posts;
    private Criteria criteria;
    private List<String> categories;
}
