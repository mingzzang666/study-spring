package com.example.practice.domain;

import com.example.practice.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @ToString
@EqualsAndHashCode(of="id")
public class CategoryVO extends Period {
    private Long id;
    private String categories;
}
