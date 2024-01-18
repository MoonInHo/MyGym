package com.innovation.mygym.member.presentation.controller;

import com.innovation.mygym.member.application.dto.CreateMemberRequestDto;
import com.innovation.mygym.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }
}
