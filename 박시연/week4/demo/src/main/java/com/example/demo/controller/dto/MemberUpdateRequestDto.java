package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {


    private String password;

    private String email;

    @Builder
    public MemberUpdateRequestDto(String password, String email){
        this.password=password;
        this.email=email;
    }



}
