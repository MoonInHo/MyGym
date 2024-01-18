package com.innovation.mygym.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 체중 도메인")
class WeightTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("체중 입력 - 체중 미입력시 예외 발생")
    void nullWeight_throwException(Double weight) {
        //given & when
        Throwable throwable = catchThrowable(() -> Weight.of(weight));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("몸무게를 입력하세요.");
    }

    @Test
    @DisplayName("체중 입력 - 올바른 형식의 체중값 입력시 체중 객체 반환")
    void properWeightValue_returnWeightObject() {
        //given & when
        Weight weight = Weight.of(76.4);

        //then
        assertThat(weight).isInstanceOf(Weight.class);
    }
}