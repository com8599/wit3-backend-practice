package com.witbackend.week8.dto.board.reply;

import com.witbackend.week8.domain.Board;
import com.witbackend.week8.domain.Reply;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyRequestDto {
    private String body;

    private Board board;

    public Reply toEntity() {
        return Reply.builder()
                .body(body)
                .board(board)
                .build();
    }
}
