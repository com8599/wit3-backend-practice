package com.witbackend.week8.dto.login;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.witbackend.week8.domain.MemberInfo;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberInfoDto from(MemberInfo memberInfo) {
        if(memberInfo == null) return null;

        return MemberInfoDto.builder()
                .email(memberInfo.getEmail())
                .nickname(memberInfo.getNickname())
                .authorityDtoSet(memberInfo.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
