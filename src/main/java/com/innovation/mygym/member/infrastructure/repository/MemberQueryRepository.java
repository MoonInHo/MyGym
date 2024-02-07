package com.innovation.mygym.member.infrastructure.repository;

import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.domain.vo.Phone;
import com.innovation.mygym.member.domain.vo.Username;

import java.util.Optional;

public interface MemberQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);

    Optional<Member> getMember(Username username);

    Optional<Long> getMemberId(Username username);
}
