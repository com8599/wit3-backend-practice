package com.witbackend.week8.dto.MemberDto;

import com.witbackend.week8.domain.Member;
import lombok.*;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    private String email;

    private String password;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
