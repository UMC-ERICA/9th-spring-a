package com.example.jerry.global.auth.enums;

import com.example.jerry.domain.entity.Member;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.MemberErrorCode;
import com.example.jerry.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new TestException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new CustomUserDetails(member);
    }
}
