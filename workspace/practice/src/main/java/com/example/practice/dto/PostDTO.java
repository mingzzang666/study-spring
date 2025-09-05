package com.example.practice.dto;


import ch.qos.logback.core.status.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Status postStatus;
    private Long memberId;
    private String[] categories;
    private String createdDatetime;
    private String updatedDatetime;
    private String relativeDate;
    private String createdDate;
    private List<FileDTO> files;
}
