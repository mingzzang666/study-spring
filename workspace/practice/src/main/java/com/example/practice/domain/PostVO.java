package com.example.practice.domain;

import com.example.practice.audit.Period;
import com.example.practice.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@SuperBuilder
@EqualsAndHashCode(of="id")
public class PostVO extends Period {
    private String id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Status postStatus;
    private Long memberId;

}
