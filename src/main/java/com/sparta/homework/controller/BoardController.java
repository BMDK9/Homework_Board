package com.sparta.homework.controller;

import com.sparta.homework.dto.BoardDeleteRequestDTO;
import com.sparta.homework.dto.BoardRequestDTO;
import com.sparta.homework.dto.BoardResponseDTO;
import com.sparta.homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ver1")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시물 단일 조회
    @GetMapping("/board/{id}")
    public BoardResponseDTO getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // 게시물 전체 조회
    @GetMapping("/totalboard")
    public List<BoardResponseDTO> getBoard() {
        return boardService.getBoard();
    }

    // 게시물 수정
    @PutMapping("/board/{id}")
    public BoardResponseDTO updateBoard(@RequestBody BoardRequestDTO boardRequestDTO, @PathVariable Long id) {
        return boardService.updateBoard(boardRequestDTO, id);
    }

    // 게시물 삭제
    @DeleteMapping("/board/{id}")
    public void deleteBoard(@RequestBody BoardDeleteRequestDTO boardDeleteRequestDTO, @PathVariable Long id) {
        boardService.deleteBoard(boardDeleteRequestDTO, id);
    }

    // 게시물 생성
    @PostMapping("/board/new")
    public BoardResponseDTO createBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        return boardService.creatBoard(boardRequestDTO);
    }
}