package com.innovation.mygym.member.domain.repository;

import com.innovation.mygym.member.domain.entity.Member;
import com.innovation.mygym.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
