package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.board.BoardRequestDto;
import com.witbackend.week8.dto.board.BoardResponseDto;
import com.witbackend.week8.dto.board.BoardUpdateRequestDto;
import com.witbackend.week8.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boards")
public class BoardApiController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findBoards() {
        return ResponseEntity.ok(boardService.findBoards());
    }

    @GetMapping("{boardId}")
    public ResponseEntity<BoardResponseDto> findBoard(@PathVariable UUID boardId) {
        return ResponseEntity.ok(boardService.findBoard(boardId));
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> saveBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok(boardService.save(boardRequestDto));
    }

    @PutMapping("{boardId}")
    public ResponseEntity<BoardResponseDto> modify(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto, @PathVariable UUID boardId) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, boardUpdateRequestDto));
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity remove(@PathVariable UUID boardId) {
        boardService.delete(boardId);
        return ResponseEntity.noContent().build();
    }
}
