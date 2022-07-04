package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.MemberDto.MemberRequestDto;
import com.witbackend.week8.dto.MemberDto.MemberResponseDto;
import com.witbackend.week8.dto.MemberDto.MemberUpdateRequestDto;
import com.witbackend.week8.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "/members";

    // 목록 GET
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> list(@RequestParam(defaultValue = "0") int page, Pageable pageable) {
        return new ResponseEntity<>(memberService.findMembers(page, pageable), HttpStatus.OK);
    }

    // 수정 GET
    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDto> mod(@PathVariable Long id) {
        if (memberService.findOne(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(memberService.findOne(id), HttpStatus.OK);
    }

    // 등록 POST
    @PostMapping
    public ResponseEntity<MemberResponseDto> postAdd(@RequestBody MemberRequestDto memberRequestDTO) {
        return new ResponseEntity<>(memberService.register(memberRequestDTO), HttpStatus.OK);
    }

    // 수정 PUT
    @PutMapping("{id}")
    public ResponseEntity<MemberResponseDto> putMod(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto, @PathVariable Long id) {
        return new ResponseEntity<>(memberService.updateMember(id, memberUpdateRequestDto), HttpStatus.OK);
    }

    // 삭제 DELETE
    @DeleteMapping("{id}")
    public ResponseEntity del(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
