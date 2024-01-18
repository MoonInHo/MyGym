package com.innovation.mygym.member.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 신장 도메인")
class HeightTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("신장 입력 - 신장 미입력시 예외 발생")
    void nullHeight_throwException(Double height) {
        //given & when
        Throwable throwable = catchThrowable(() -> Height.of(height));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("신장을 입력하세요.");
    }

    @Test
    @DisplayName("신장 입력 - 올바른 형식의 신장값 입력시 신장 객체 반환")
    void properHeightValue_returnHeightObject() {
        //given & when
        Height height = Height.of(181.4);

        //then
        assertThat(height).isInstanceOf(Height.class);
    }
}