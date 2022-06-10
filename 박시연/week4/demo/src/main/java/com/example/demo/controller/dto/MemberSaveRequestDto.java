package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class MemberSaveRequestDto {
    private Long id;
    private String email;

    private String password;

    @Builder
    public MemberSaveRequestDto(Long id, String email, String password){
        this.id=id;
        this.email=email;
        this.password=password;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
