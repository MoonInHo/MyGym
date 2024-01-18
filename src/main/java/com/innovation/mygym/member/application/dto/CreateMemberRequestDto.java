package com.innovation.mygym.member.application.dto;

import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.domain.enums.Gender;
import com.innovation.mygym.member.domain.vo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto { //TODO 해당 Dto 가 표현 영역과 응용 영역중 어느곳에 있는게 적절한지 판단 후 변경

    private String username;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private Double height;
    private Double weight;

    public Member toEntity() {
        return Member.createMember(
                Username.of(username),
                Password.of(password),
                Name.of(name),
                Age.of(age),
                Gender.valueOf(gender),
                Phone.of(phone),
                Height.of(height),
                Weight.of(weight)
        );
    }
}
