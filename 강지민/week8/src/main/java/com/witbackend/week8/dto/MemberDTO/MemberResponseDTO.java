package com.witbackend.week8.dto.MemberDto;

import com.witbackend.week8.domain.Member;
import lombok.*;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;

    private String email;

    private String password;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
