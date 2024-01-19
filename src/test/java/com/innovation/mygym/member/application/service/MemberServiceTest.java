package com.innovation.mygym.member.application.service;

import com.innovation.mygym.exception.exception.member.DuplicatePhoneException;
import com.innovation.mygym.exception.exception.member.DuplicateUsernameException;
import com.innovation.mygym.member.application.dto.CreateMemberRequestDto;
import com.innovation.mygym.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 회원 서비스")
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입 - 중복된 아이디로 회원가입 시도시 예외 발생")
    void duplicateUsername_signUp_throwException() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "duplicateUsername",
                "testPassword123!",
                "김개발",
                30,
                "MALE",
                "010-1234-5678",
                182.5,
                76.3
        );
        given(memberRepository.isUsernameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(createMemberRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicateUsernameException.class);
        assertThat(throwable).hasMessage("해당 아이디가 이미 존재합니다.");
    }

    @Test
    @DisplayName("회원 가입 - 중복된 연락처로 회원가입 시도시 예외 발생")
    void duplicatePhone_signUp_throwException() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "test123",
                "testPassword123!",
                "김개발",
                30,
                "MALE",
                "010-1234-5678",
                182.5,
                76.3
        );
        given(memberRepository.isPhoneExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(createMemberRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicatePhoneException.class);
        assertThat(throwable).hasMessage("해당 연락처로 가입 정보가 존재합니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 형식의 사용자 정보를 입력받아 회원가입 시도시 회원 정보 생성")
    void properUserInfo_signUp_createMember() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "test123",
                "testPassword123!",
                "김개발",
                30,
                "MALE",
                "010-1234-5678",
                182.5,
                76.3
        );
        given(memberRepository.isUsernameExist(any())).willReturn(false);
        given(memberRepository.isPhoneExist(any())).willReturn(false);

        //when
        memberService.signUp(createMemberRequestDto);

        //then
        verify(memberRepository, times(1)).save(any());
    }
}