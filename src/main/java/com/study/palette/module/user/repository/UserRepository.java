package com.study.palette.module.user.repository;

import com.study.palette.module.user.entity.SocialType;
import com.study.palette.module.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findById(UUID id);

  boolean existsByEmail(String email);

  Optional<User> findByNameAndPhone(String email, String phone);

  Page<User> findAll(Pageable pageable);

  void deleteById(UUID id);

  Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String id);
}