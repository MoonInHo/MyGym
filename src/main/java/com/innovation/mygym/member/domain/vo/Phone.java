package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Phone {

    private final String phone;

    private Phone(String phone) {
        this.phone = phone;
    }

    public static Phone of(String phone) {

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("연락처를 입력하세요.");
        }
        if (phone.contains(" ")) {
            throw new IllegalArgumentException("연락처엔 공백을 포함할 수 없습니다.");
        }
        return new Phone(phone);
    }
}
