package com.witbackend.week6.controller.api;

import com.witbackend.week6.dto.MemberDTO.MemberRequestDTO;
import com.witbackend.week6.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "http://localhost:8080/members/list";

    // request가 form 형태이고, @ResponseBody가 data를 JSON으로 인식하여 415에러 발생
    // -> consumes = APPLICATION_FORM_URLENCODED_VALUE 추가
    // @RestController로 바꾸었기 때문에 return "rediect:members/list"가 문자열로 리턴됨.
    // -> 타입을 String에서 void로 바꾸고 HttpServletResponse를 추가하여 redirect를 가능하게 함.
    // 2022/06/27 수업시작하기 전 점검 중 또 415에러 발생, 구글링 한 결과 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE를 쓰게 되면 스프링에서 RequestBody를 인식하지 못한다고 함. 따라서 @RequestBody를 반드시 없애줘야 한다고 함.
    // https://stackoverflow.com/questions/33796218/content-type-application-x-www-form-urlencodedcharset-utf-8-not-supported-for
    // 프론트 세계를 잘 몰라서 form 태그로 했는데, 다음부턴 그냥 ajax로 json 통신 해야겠다... 둘이 서로 받는 형식이 다르니 설정이 복잡 ㅠㅠ

    // 등록 post 통신
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void postAdd(MemberRequestDTO memberRequestDTO, HttpServletResponse response) throws IOException {
        memberService.register(memberRequestDTO);
        response.sendRedirect(REDIRECT_TO_LIST);
    }

    // 수정 post 통신
    @PutMapping(value = "mod/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void postMod(MemberRequestDTO memberRequestDTO, HttpServletResponse response) throws IOException {
        memberService.register(memberRequestDTO);
        response.sendRedirect(REDIRECT_TO_LIST);
    }

    // 삭제
    @DeleteMapping(value = "del/{id}")
    public void del(@PathVariable Long id, HttpServletResponse response) throws IOException {
        memberService.delete(id);
        response.sendRedirect(REDIRECT_TO_LIST);
    }
}
