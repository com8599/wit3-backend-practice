package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class MemberSaveRequestDto {
    public Long id;
    public String email;

    @Builder
    public MemberSaveRequestDto(Long id, String email){
        this.id=id;
        this.email=email;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .build();
    }
}
