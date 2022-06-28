package com.witbackend.week8.dto.MemberDTO;

import com.witbackend.week8.domain.Member;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@Builder
public class MemberResponseDTO {
    private Long id;

    private String email;

    private String password;

    public MemberResponseDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
