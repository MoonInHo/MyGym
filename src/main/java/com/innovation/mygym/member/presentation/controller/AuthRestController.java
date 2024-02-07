package com.innovation.mygym.member.presentation.controller;

import com.innovation.mygym.member.application.security.AccountContext;
import com.innovation.mygym.member.application.service.AuthService;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(
            HttpServletResponse response,
            @RequestBody SignInRequestDto signInRequestDto
    ) {
        response.setHeader("Authorization", "Bearer " + authService.signIn(signInRequestDto)); //TODO 적절한 코드인지 검증
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(Authentication authentication) {
        authService.signOut(authentication);
        return ResponseEntity.ok().build();
    }
}
