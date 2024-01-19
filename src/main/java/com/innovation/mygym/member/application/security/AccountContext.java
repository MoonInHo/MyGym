package com.innovation.mygym.member.application.security;

import com.innovation.mygym.member.domain.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Member member;

    public AccountContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.username(), member.password(), authorities);
        this.member = member;
    }
}
