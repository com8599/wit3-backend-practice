package com.witbackend.week6.dto.MemberDTO;

import com.witbackend.week6.domain.Member;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDTO {
    private Long id;

    private String email;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
