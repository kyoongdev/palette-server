package com.study.palette.module.users.repository;

import com.study.palette.module.users.entity.RefreshToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByUsersId(UUID id);

  void deleteByUsersId(UUID id);
}
