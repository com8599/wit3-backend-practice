package com.witbackend.week8.dto.board.reply;

import com.witbackend.week8.domain.Reply;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyResponseDto {
    private UUID replyId;

    private String body;

    private UUID boardId;

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getReplyId();
        this.body = reply.getBody();
        this.boardId = reply.getBoard().getBoardId();
    }
}
