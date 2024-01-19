package com.innovation.mygym.member.presentation.controller;

import com.innovation.mygym.member.application.dto.CreateMemberRequestDto;
import com.innovation.mygym.member.application.security.JwtUtil;
import com.innovation.mygym.member.application.service.MemberService;
import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.presentation.dto.SignInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.username(),
                        signInRequestDto.password()
                )
        );
        Member member = (Member) authenticate.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(member.username()));
    }

    //TODO 로그아웃 기능 구현
}
