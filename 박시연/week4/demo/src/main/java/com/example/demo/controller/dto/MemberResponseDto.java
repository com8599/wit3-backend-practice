package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    final Long id;

    final String email;

    public MemberResponseDto(Member entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
    }
}
