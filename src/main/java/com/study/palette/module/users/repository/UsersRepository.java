package com.study.palette.module.users.repository;

import com.study.palette.module.users.entity.SocialType;
import com.study.palette.module.users.entity.Users;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);

  Optional<Users> findById(UUID id);

  boolean existsByEmail(String email);

  Optional<Users> findByNameAndPhone(String email, String phone);

  Page<Users> findAll(Pageable pageable);

  void deleteById(UUID id);

  Optional<Users> findBySocialTypeAndSocialId(SocialType socialType, String id);
}