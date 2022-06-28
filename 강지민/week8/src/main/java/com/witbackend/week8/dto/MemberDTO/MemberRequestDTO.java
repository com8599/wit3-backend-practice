package com.witbackend.week8.dto.MemberDTO;

import com.witbackend.week8.domain.Member;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDTO {
    private String email;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
