package com.sparta.homework.entity;

import com.sparta.homework.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1씩 증가
    private Long id;

    private String title;
    private String writer;
    private String password;
    private String contents;

    @Builder
    public Board(String title, String writer, String password, String contents) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.contents = contents;
    }

    public void update(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }
}
