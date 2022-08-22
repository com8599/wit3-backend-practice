package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.board.reply.ReplyRequestDto;
import com.witbackend.week8.dto.board.reply.ReplyResponseDto;
import com.witbackend.week8.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/replies/{boardId}")
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponseDto> replySave(@PathVariable UUID boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        return ResponseEntity.ok(replyService.replySave(boardId, replyRequestDto));
    }
}
