package com.witbackend.week8.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberInfoId;

    private String email;

    private String password;

    private String nickname;

    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "member_info_authority",
            joinColumns = {@JoinColumn(name = "member_info_id", referencedColumnName = "memberInfoId")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authorityName")})
    private Set<Authority> authorities;
}
