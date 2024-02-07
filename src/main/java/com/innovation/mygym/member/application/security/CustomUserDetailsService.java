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
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.getMember(Username.of(username))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인해주세요."));

        return new AccountContext(username, member.password(), member.createRole(), member.id());
    }
}
