package com.sparta.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {

    private String title;
    private String writer;
    private String password;
    private String contents;

}
