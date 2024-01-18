package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Age {

    private final Integer age;

    private Age(Integer age) {
        this.age = age;
    }

    public static Age of(Integer age) {

        if (age == null) {
            throw new IllegalArgumentException("나이를 입력하세요.");
        }
        return new Age(age);
    }
}
