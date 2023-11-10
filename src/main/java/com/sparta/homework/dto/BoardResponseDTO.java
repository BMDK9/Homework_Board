package com.sparta.homework.dto;

import com.sparta.homework.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String writer;
    private String contents;

    @Builder
    private BoardResponseDTO(Long id, String title, String writer, String contents) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public static BoardResponseDTO of(Board board) {
        return BoardResponseDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writer(board.getWriter())
                .contents(board.getContents())
                .build();
    }
}
