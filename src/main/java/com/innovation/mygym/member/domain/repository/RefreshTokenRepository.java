package com.innovation.mygym.member.domain.repository;

import com.innovation.mygym.member.domain.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
