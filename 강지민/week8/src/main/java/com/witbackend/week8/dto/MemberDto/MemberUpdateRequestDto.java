package com.witbackend.week8.dto.MemberDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String email;

    private String password;

    public MemberUpdateRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

