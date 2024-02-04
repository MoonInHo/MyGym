package com.innovation.mygym.member.application.service;

import com.innovation.mygym.member.application.security.JwtAuthProvider;
import com.innovation.mygym.member.domain.entity.RefreshToken;
import com.innovation.mygym.member.domain.repository.RefreshTokenRepository;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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

        refreshTokenRepository.save(new RefreshToken(refreshToken, accessToken));

        return accessToken;
    }
    //TODO refresh token 을 이용한 access token 갱신 방법 찾아보기
}
