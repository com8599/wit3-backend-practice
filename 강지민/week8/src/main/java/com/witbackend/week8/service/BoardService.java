package com.witbackend.week8.service;

import com.witbackend.week8.domain.Board;
import com.witbackend.week8.dto.board.BoardRequestDto;
import com.witbackend.week8.dto.board.BoardResponseDto;
import com.witbackend.week8.dto.board.BoardUpdateRequestDto;
import com.witbackend.week8.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponseDto> findBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto findBoard(UUID boarId) {
        Board board = boardRepository.findById(boarId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new BoardResponseDto(board);
    }

    public BoardResponseDto save(BoardRequestDto boardRequestDto) {
        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public BoardResponseDto updateBoard(UUID boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getBody());

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    public void delete(UUID boardId) {
        boardRepository.deleteById(boardId);
    }
}
