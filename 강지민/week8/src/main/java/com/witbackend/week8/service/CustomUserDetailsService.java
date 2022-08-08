package com.witbackend.week8.service;

import com.witbackend.week8.domain.MemberInfo;
import com.witbackend.week8.repository.MemberInfoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberInfoRepository memberInfoRepository;

    public CustomUserDetailsService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        return memberInfoRepository.findOneWithAuthoritiesByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, MemberInfo memberInfo) {
        if (!memberInfo.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = memberInfo.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(memberInfo.getUsername(),
                memberInfo.getPassword(),
                grantedAuthorities);
    }
}
