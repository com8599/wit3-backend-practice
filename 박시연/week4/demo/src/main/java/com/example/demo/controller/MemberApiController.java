package com.example.demo.controller;

import com.example.demo.controller.dto.MemberResponseDto;
import com.example.demo.controller.dto.MemberSaveRequestDto;
import com.example.demo.controller.dto.MemberUpdateRequestDto;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tuple.InMemoryValueGenerationStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;


//@RestController사용 -> 모든 메소드가 객체로 작성됨
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberApiController {

    private final MemberService memberService;

    //CRUD 작성

    //CREATE

    //postmapping은 마지막에 붙여주기
    //@RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostMapping()
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberSaveRequestDto requestDto){
        return new ResponseEntity<>(memberService.save(requestDto), HttpStatus.OK);
    }


    //READ

    //1. id 특정해서 한개만 읽기
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberResponseDto> readMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.readOne(id), HttpStatus.OK);
    }

    //2. 전부다 읽기
    //리스트 사용
    @GetMapping()
    public ResponseEntity<List<MemberResponseDto>> readAllMembers(@RequestParam int page, Pageable pageable){
        return new ResponseEntity<>(memberService.findMembersByPageRequest(pageable),HttpStatus.OK);
    }


    //UPDATE

    //특정하는 작업 먼저 하고
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto){
        return new ResponseEntity<>( memberService.update(id, requestDto), HttpStatus.OK);
    }


    //DELETE

    //id로 어떤거 지울지 특정
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
