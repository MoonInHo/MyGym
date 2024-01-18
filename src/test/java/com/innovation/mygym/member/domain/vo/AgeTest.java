package com.innovation.mygym.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 나이 도메인")
class AgeTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("나이 입력 - 나이 미입력시 예외 발생")
    void nullAge_throwException(Integer age) {
        //given & when
        Throwable throwable = catchThrowable(() -> Age.of(age));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("나이를 입력하세요.");
    }

    @Test
    @DisplayName("나이 입력 - 올바른 형식의 나이값 입력시 나이 객체 반환")
    void properAgeValue_returnAgeObject() {
        //given & when
        Age age = Age.of(30);

        //then
        assertThat(age).isInstanceOf(Age.class);
    }
}