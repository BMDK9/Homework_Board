package com.sparta.homework.service;

import com.sparta.homework.dto.BoardDeleteRequestDTO;
import com.sparta.homework.dto.BoardRequestDTO;
import com.sparta.homework.dto.BoardResponseDTO;
import com.sparta.homework.entity.Board;
import com.sparta.homework.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDTO creatBoard(BoardRequestDTO boardRequestDTO) {
        // boardRequestDTO 를 entity로
        Board board = Board.builder()
                .title(boardRequestDTO.getTitle())
                .writer(boardRequestDTO.getWriter())
                .password(boardRequestDTO.getPassword())
                .contents(boardRequestDTO.getContents())
                .build();

        // entity를 repository 저장
        Board saveBoard = boardRepository.save(board);

        // 저장 후 responseDTO 반환
        return BoardResponseDTO.of(saveBoard);
    }

    public BoardResponseDTO getBoard(Long id) {
        // repository에서 입력된 id값 데이터 추출
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다."));

        // entity를 responseDTO로
        return BoardResponseDTO.of(board);
    }

    public List<BoardResponseDTO> getBoard() {
//        // repository 모든 Board 를 가져온다.
//        List<Board> boards = boardRepository.findAll();
//
//        // 위의 것을 List인 ResponseDTO로 변환하고 반환한다.
//        List<BoardResponseDTO> list = new ArrayList<>();
//
//        for (Board g : boards) {
//            list.add(BoardResponseDTO.of(g));
//        }
//        return list;

//        생성 시간별 정렬
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();

        List<BoardResponseDTO> list = boards.stream()
                .map(BoardResponseDTO::of)
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public BoardResponseDTO updateBoard(BoardRequestDTO boardRequestDTO, Long id) {
        // repository에서 입력된 id값 데이터 추출
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        if (board.getPassword().equals(boardRequestDTO.getPassword())) {
            // 같을 때 로직 처리
            board.update(boardRequestDTO.getTitle(), boardRequestDTO.getWriter(), boardRequestDTO.getContents());
            return BoardResponseDTO.of(board);
        } else {
            return null;
        }
    }

    public void deleteBoard(BoardDeleteRequestDTO boardDeleteRequestDTO, Long id) {
        // repository에서 입력된 id값 데이터 추출
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        if (board.getPassword().equals(boardDeleteRequestDTO.getPassword())) {
            boardRepository.delete(board);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}