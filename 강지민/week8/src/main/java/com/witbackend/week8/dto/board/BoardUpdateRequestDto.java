package com.witbackend.week8.dto.board;

import com.witbackend.week8.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardUpdateRequestDto {
    private String title;

    private String body;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .body(body)
                .build();
    }
}
