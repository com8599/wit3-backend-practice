package com.witbackend.week8.dto.login;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    // accessToken
    private String token;

    // refreshToken
    private String refreshToken;
}
