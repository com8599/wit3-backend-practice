package com.witbackend.week8.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String email;

    private String password;

    @Builder
    public MemberUpdateRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

