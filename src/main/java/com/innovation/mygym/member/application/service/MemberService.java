package com.innovation.mygym.member.application.service;

import com.innovation.mygym.exception.exception.member.DuplicatePhoneException;
import com.innovation.mygym.exception.exception.member.DuplicateUsernameException;
import com.innovation.mygym.member.application.dto.CreateMemberRequestDto;
import com.innovation.mygym.member.domain.repository.MemberRepository;
import com.innovation.mygym.member.domain.vo.Phone;
import com.innovation.mygym.member.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(CreateMemberRequestDto createMemberRequestDto) {

        /**
         * 아이디 중복 체크 처리 방식 고려
         * 1. javascript & ajax 를 이용한 비동기 처리
         * 2. 수동으로 중복 체크 (별도의 API 요청)
         */
        if (isDuplicateUsername(createMemberRequestDto)) {
            throw new DuplicateUsernameException();
        }
        if (isDuplicatePhone(createMemberRequestDto)) {
            throw new DuplicatePhoneException();
        }
        memberRepository.save(createMemberRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    protected boolean isDuplicateUsername(CreateMemberRequestDto createMemberRequestDto) {
        return memberRepository.isUsernameExist(Username.of(createMemberRequestDto.getUsername()));
    }

    @Transactional(readOnly = true)
    protected boolean isDuplicatePhone(CreateMemberRequestDto createMemberRequestDto) {
        return memberRepository.isPhoneExist(Phone.of(createMemberRequestDto.getPhone()));
    }
}