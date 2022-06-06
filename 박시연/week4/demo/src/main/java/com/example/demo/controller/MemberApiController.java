package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    //rest 컨트롤러 써서 @ResponseBody 안써도됨
    public String createMember(Member member){
        memberService.save(member);
        return "create";
    }


    //READ

    //1. id 특정해서 한개만 읽기
    @GetMapping(value = "{id}")
    public String readMember(@PathVariable Long id) {
        memberService.readOne(id);
        return "read";
    }

    //2. 전부다 읽기
    //리스트 사용
    @GetMapping()
    public List<Member> readAllMembers(){
        List<Member> members = memberService.readAll();
        return members;
    }

    //UPDATE

    //id로 특정하는 작업 먼저 하고
    //create에서 사용한 save를 그대로 사용해서 save를 통한 update
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Member member){
        memberService.save(member);
        return "update";
    }


    //DELETE

    //id로 어떤거 지울지 특정
    @RequestMapping("/delete/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.delete(id);
    }



}
