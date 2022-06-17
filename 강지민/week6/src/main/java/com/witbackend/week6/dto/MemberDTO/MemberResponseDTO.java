package com.witbackend.week6.dto.MemberDTO;

import com.witbackend.week6.domain.Member;
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
//        MemberResponseDTO.builder()
//                .id(member.getId())
//                .email(member.getEmail())
//                .password(member.getPassword())
//                .build();
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
