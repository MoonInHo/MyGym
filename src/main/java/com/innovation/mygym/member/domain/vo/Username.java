package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Username {

    private final String username;

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("아이디를 입력하세요.");
        }
        if (username.contains(" ")) {
            throw new IllegalArgumentException("아이디엔 공백을 포함할 수 없습니다.");
        }
        return new Username(username);
    }
}
