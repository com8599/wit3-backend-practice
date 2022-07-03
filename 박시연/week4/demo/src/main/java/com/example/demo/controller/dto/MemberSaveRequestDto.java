package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class MemberSaveRequestDto {
    private String email;

    private String password;

    @Builder
    public MemberSaveRequestDto(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
