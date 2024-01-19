package com.innovation.mygym.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Role {

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    public static Role of(String role) {
        return new Role(role);
    }

    public SimpleGrantedAuthority createRole() {
        return new SimpleGrantedAuthority(this.role);
    }
}
