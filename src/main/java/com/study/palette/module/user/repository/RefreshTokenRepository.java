package com.study.palette.module.user.repository;

import com.study.palette.module.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(UUID id);
    void deleteByUserId(UUID id);
}
