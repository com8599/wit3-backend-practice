package com.witbackend.week8.dto.board;

import com.witbackend.week8.domain.Board;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
    private UUID boardId;

    private String title;

    private String body;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.body = board.getBody();
    }
}
