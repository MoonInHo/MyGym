package com.innovation.mygym.member.domain.entity;

import com.innovation.mygym.member.domain.enums.Gender;
import com.innovation.mygym.member.domain.enums.Grade;
import com.innovation.mygym.member.domain.enums.Role;
import com.innovation.mygym.member.domain.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member { //TODO 각 필드의 형식 검증에 Bean validation 사용 하는 것을 고려해보기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private Username username;

    @Embedded
    @Column(nullable = false)
    private Password password;

    @Embedded
    @Column(nullable = false)
    private Name name;

    @Embedded
    @Column(nullable = false)
    private Age age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Embedded
    @Column(nullable = false, unique = true)
    private Phone phone;

    @Embedded
    @Column(nullable = false)
    private Height height; //TODO 소수점 1자리 까지만 표기

    @Embedded
    @Column(nullable = false)
    private Weight weight; //TODO 소수점 1자리 까지만 표기

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private Member(
            Username username,
            Password password,
            Name name,
            Age age,
            Gender gender,
            Phone phone,
            Height height,
            Weight weight
    ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.grade = Grade.STARTER;
        this.role = Role.ROLE_USER;
    }

    public static Member createMember(
            Username username,
            Password password,
            Name name,
            Age age,
            Gender gender,
            Phone phone,
            Height height,
            Weight weight
    ) {
        return new Member(username, password, name, age, gender, phone, height, weight);
    }

    public void passwordEncrypt(PasswordEncoder passwordEncoder) {
        this.password = password.encodedPassword(passwordEncoder);
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole());
    }

    public String username() {
        return username.username();
    }

    public String password() {
        return password.password();
    }
}
