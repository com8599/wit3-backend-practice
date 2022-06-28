package com.witbackend.week8.controller.view;

import com.witbackend.week8.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberViewController {
    private final MemberService memberService;

}
