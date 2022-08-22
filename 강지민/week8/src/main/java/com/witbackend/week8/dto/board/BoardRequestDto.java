package com.witbackend.week8.dto.board;

import com.witbackend.week8.domain.Board;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequestDto {
    private String title;

    private String body;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .body(body)
                .build();
    }
}
