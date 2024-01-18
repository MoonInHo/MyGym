package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Weight {

    private final Double weight;

    private Weight(Double weight) {
        this.weight = weight;
    }

    public static Weight of(Double weight) {

        if (weight == null) {
            throw new IllegalArgumentException("몸무게를 입력하세요.");
        }
        return new Weight(weight);
    }
}
