package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.MemberDTO.MemberRequestDTO;
import com.witbackend.week8.dto.MemberDTO.MemberResponseDTO;
import com.witbackend.week8.dto.MemberDTO.MemberUpdateRequestDto;
import com.witbackend.week8.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "/members";

    // 목록 GET
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> list() {
        return new ResponseEntity<>(memberService.findMembers(), HttpStatus.OK);
    }

    // 수정 GET
    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDTO> mod(@PathVariable Long id) {
        if (memberService.findOne(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(memberService.findOne(id), HttpStatus.OK);
    }

    // 등록 POST
    @PostMapping
    public ResponseEntity<MemberResponseDTO> postAdd(@RequestBody MemberRequestDTO memberRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(REDIRECT_TO_LIST));
        return new ResponseEntity<>(memberService.register(memberRequestDTO), headers, HttpStatus.SEE_OTHER);
    }

    // 수정 PUT
    @PutMapping("{id}")
    public ResponseEntity<MemberResponseDTO> putMod(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto, @PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(REDIRECT_TO_LIST));
        return new ResponseEntity<>(memberService.updateMember(id, memberUpdateRequestDto), headers, HttpStatus.SEE_OTHER);
    }

    // 삭제 DELETE
    @DeleteMapping("{id}")
    public ResponseEntity del(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
