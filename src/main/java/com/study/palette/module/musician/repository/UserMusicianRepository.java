package com.study.palette.module.musician.repository;

import com.study.palette.module.musician.entity.UserMusician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMusicianRepository extends JpaRepository<UserMusician, String> {
}
