package com.innovation.mygym.member.presentation.controller;

import com.innovation.mygym.member.application.dto.TokenDto;
import com.innovation.mygym.member.application.service.AuthService;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthRestController {

    public final String AUTHORIZATION_HEADER = "Authorization";
    public final String GRANT_TYPE = "Bearer";

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(
            HttpServletResponse response,
            @RequestBody SignInRequestDto signInRequestDto
    ) {
        TokenDto tokenDto = authService.signIn(signInRequestDto);

        setAuthorizationHeaderWithAccessToken(response, tokenDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        authService.signOut(authorizationHeader);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<Void> reissue(
            HttpServletResponse response,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        TokenDto tokenDto = authService.reissue(authorizationHeader);

        setAuthorizationHeaderWithAccessToken(response, tokenDto);

        return ResponseEntity.ok().build();
    }

    private void setAuthorizationHeaderWithAccessToken(HttpServletResponse response, TokenDto tokenDto) {
        response.setHeader(AUTHORIZATION_HEADER, GRANT_TYPE + " " + tokenDto.getAccessToken());
    }
}
