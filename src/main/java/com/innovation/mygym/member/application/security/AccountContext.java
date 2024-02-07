package com.innovation.mygym.member.application.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Long memberId;

    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities, Long memberId) {
        super(username, password, authorities);
        this.memberId = memberId;
    }
}