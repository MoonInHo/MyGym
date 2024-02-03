package com.innovation.mygym.member.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 1209600) //TODO 토큰이 만료된 뒤 삭제 되지않고 남은 key 들을 정리할 방법 찾아보기
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    @Indexed
    private String accessToken;

    public RefreshToken(
            String refreshToken,
            String accessToken
    ) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
