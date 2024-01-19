package com.innovation.mygym.member.application.security;

import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.domain.repository.MemberRepository;
import com.innovation.mygym.member.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.getMember(Username.of(username))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 올바르지 않습니다."));

        return new AccountContext(member, member.createRole());
    }
}
