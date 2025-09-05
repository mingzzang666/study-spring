package com.example.practice.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(force = true)

public abstract class Period {
    private String createdDate;
    private String updatedDate;
}
