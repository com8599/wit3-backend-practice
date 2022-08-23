package com.witbackend.week8.dto.login;

import com.witbackend.week8.domain.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
    private Role authorityName;
}
