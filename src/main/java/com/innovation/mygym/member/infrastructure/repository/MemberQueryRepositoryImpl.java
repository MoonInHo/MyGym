package com.innovation.mygym.member.infrastructure.repository;

import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.domain.vo.Phone;
import com.innovation.mygym.member.domain.vo.Username;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.innovation.mygym.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isUsernameExist(Username username) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.username.eq(username))
                .fetchFirst() != null;
    }

    @Override
    public boolean isPhoneExist(Phone phone) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.phone.eq(phone))
                .fetchFirst() != null;
    }

    @Override
    public Optional<Member> getMember(Username username) {
        Member result = queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
