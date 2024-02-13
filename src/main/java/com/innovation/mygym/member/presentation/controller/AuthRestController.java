package com.innovation.mygym.member.presentation.controller;

import com.innovation.mygym.member.application.dto.TokenDto;
import com.innovation.mygym.member.application.service.AuthService;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthRestController {

    private final int refreshTokenExpire;
    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.refreshTokenExpire = 1000 * 60 * 60 * 24 * 14; // 14일
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(
            HttpServletResponse response,
            @RequestBody SignInRequestDto signInRequestDto
    ) {
        TokenDto tokenDto = authService.signIn(signInRequestDto);

        setAuthorizationHeaderWithAccessToken(response, tokenDto);
        addRefreshTokenCookie(response, tokenDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            @RequestHeader("Authorization") String authorizationHeader,
            HttpServletResponse response
    ) {
        authService.signOut(authorizationHeader);

        expireRefreshTokenCookie(response);

        return ResponseEntity.ok().build();
    }

    private void setAuthorizationHeaderWithAccessToken(HttpServletResponse response, TokenDto tokenDto) {
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
    }

    private void addRefreshTokenCookie(HttpServletResponse response, TokenDto tokenDto) {
        Cookie refreshTokenCookie = new Cookie("Refresh-Token", tokenDto.getRefreshToken());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(refreshTokenExpire);
        response.addCookie(refreshTokenCookie);
    }

    private void expireRefreshTokenCookie(HttpServletResponse response) {
        Cookie expiredCookie = new Cookie("Refresh-Token", null);
        expiredCookie.setPath("/");
        expiredCookie.setHttpOnly(true);
        expiredCookie.setMaxAge(0);
        response.addCookie(expiredCookie);
    }
}
