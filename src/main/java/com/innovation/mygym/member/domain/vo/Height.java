package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Height {

    private final Double height;

    private Height(Double height) {
        this.height = height;
    }

    public static Height of(Double height) {

        if (height == null) {
            throw new IllegalArgumentException("신장을 입력하세요.");
        }
        return new Height(height);
    }
}
