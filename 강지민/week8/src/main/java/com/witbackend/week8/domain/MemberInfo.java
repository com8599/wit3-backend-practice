package com.witbackend.week8.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "memberInfo")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {

    @Id
    @Column(name = "member_info_id")    // 컬럼 네임 삭제
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberInfoId;    // UUID or ObjectId

    @Column(name = "username", length = 50, unique = true)    // 컬럼 네임 삭제
    private String username;    // rename to familiar name
    // loginId나 email로 바꿔주세요

    @Column(name = "password", length = 100)    // 컬럼 네임 삭제
    private String password;

    @Column(name = "nickname", length = 50)    // 컬럼 네임 삭제
    private String nickname;

    @Column(name = "activated")    // 컬럼 네임 삭제
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "member_info_authority",
            joinColumns = {@JoinColumn(name = "member_info_id", referencedColumnName = "member_info_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
