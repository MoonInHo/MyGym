package com.innovation.mygym.member.application.service;

import com.innovation.mygym.member.application.security.AccountContext;
import com.innovation.mygym.member.application.security.JwtAuthProvider;
import com.innovation.mygym.member.domain.entity.RefreshToken;
import com.innovation.mygym.member.domain.repository.MemberRepository;
import com.innovation.mygym.member.domain.repository.RefreshTokenRepository;
import com.innovation.mygym.member.domain.vo.Username;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String signIn(SignInRequestDto signInRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.username(),
                        signInRequestDto.password()
                )
        );
        String accessToken = jwtAuthProvider.generateAccessToken(authentication);
        String refreshToken = jwtAuthProvider.generateRefreshToken();

        AccountContext principal = (AccountContext) authentication.getPrincipal();
        Long memberId = principal.getMemberId();

        refreshTokenRepository.save(new RefreshToken(memberId, refreshToken));

        return accessToken;
    }

    @Transactional
    public void signOut(Authentication authentication) {

        String username = (String) authentication.getPrincipal();

        Long memberId = memberRepository.getMemberId(Username.of(username))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인해주세요."));
        refreshTokenRepository.deleteById(memberId);
    }
    //TODO refresh token 을 이용한 access token 갱신 구현
}
