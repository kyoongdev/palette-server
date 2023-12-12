package com.study.palette.module.mrBeat.repository;

import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatRequest;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MrBeatRequestRepository extends JpaRepository<MrBeatRequest, String> {

  @Query("select a from MrBeatRequest a where a.users = :users and a.mrBeatInfo = :mrBeatInfo and a.createAt = :now")
  Optional<MrBeatRequest> findByMrBeatInfoAndUserAndCreatedAt(MrBeatInfo mrBeatInfo,
      Users users, LocalDate now);

}
