package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private Long id;

    private String password;

    private String email;

    @Builder
    public MemberUpdateRequestDto(Long id, String password, String email){
        this.id=id;
        this.password=password;
        this.email=email;
    }



}
