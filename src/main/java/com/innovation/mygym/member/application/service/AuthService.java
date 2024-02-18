package com.innovation.mygym.member.application.service;

import com.innovation.mygym.exception.exception.auth.ExpiredRefreshTokenException;
import com.innovation.mygym.member.application.dto.TokenDto;
import com.innovation.mygym.member.application.security.AccountContext;
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
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final String GRANT_TYPE = "Bearer";

    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenDto signIn(SignInRequestDto signInRequestDto) {

        Authentication authentication = getAuthentication(signInRequestDto);

        Long memberId = getMemberId(authentication);
        if (isRefreshTokenExist(memberId)) {
            refreshTokenRepository.deleteById(memberId);
        }

        String accessToken = jwtAuthProvider.generateAccessToken(authentication);
        String refreshToken = jwtAuthProvider.generateRefreshToken();

        refreshTokenRepository.save(new RefreshToken(memberId, refreshToken));

        return new TokenDto(accessToken, refreshToken);
    }

    @Transactional
    public void signOut(String authorizationHeader) {

        String accessToken = resolveToken(authorizationHeader);

        Long memberId = jwtAuthProvider.getUserId(accessToken);
        validateRefreshTokenExistence(memberId);

        refreshTokenRepository.deleteById(memberId);
    }

    @Transactional
    public TokenDto reissue(String authorizationHeader) {

        String accessToken = resolveToken(authorizationHeader);

        Long memberId = jwtAuthProvider.getUserId(accessToken);
        validateRefreshTokenExistence(memberId);

        Authentication authentication = jwtAuthProvider.getAuthentication(accessToken);

        return new TokenDto(jwtAuthProvider.generateAccessToken(authentication), null);
    }

    private Authentication getAuthentication(SignInRequestDto signInRequestDto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.username(),
                        signInRequestDto.password()
                )
        );
    }

    private Long getMemberId(Authentication authentication) {
        AccountContext principal = (AccountContext) authentication.getPrincipal();
        return principal.getMemberId();
    }

    private boolean isRefreshTokenExist(Long memberId) {
        return refreshTokenRepository.existsById(memberId);
    }

    private void validateRefreshTokenExistence(Long memberId) {
        if (!isRefreshTokenExist(memberId)) {
            throw new ExpiredRefreshTokenException();
        }
    }

    private String resolveToken(String authorizationHeader) {
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(GRANT_TYPE)) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
