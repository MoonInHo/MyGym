package com.innovation.mygym.member.infrastructure.repository;

import com.innovation.mygym.member.domain.vo.Phone;
import com.innovation.mygym.member.domain.vo.Username;

public interface MemberQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);
}
