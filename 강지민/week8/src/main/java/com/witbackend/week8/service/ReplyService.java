package com.witbackend.week8.service;

import com.witbackend.week8.domain.Board;
import com.witbackend.week8.domain.Reply;
import com.witbackend.week8.dto.board.reply.ReplyRequestDto;
import com.witbackend.week8.dto.board.reply.ReplyResponseDto;
import com.witbackend.week8.repository.ReplyRepository;
import com.witbackend.week8.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public ReplyResponseDto replySave(UUID boardId, ReplyRequestDto replyRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패"));

        replyRequestDto.setBoard(board);

        Reply reply = replyRequestDto.toEntity();
        replyRepository.save(reply);

        return new ReplyResponseDto(reply);
    }
}
