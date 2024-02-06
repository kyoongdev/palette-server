package com.study.palette.module.mixMastering.repository;

import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringRequest;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MixMasteringRequestRepository extends JpaRepository<MixMasteringRequest, String> {

  @Query("select a from MixMasteringRequest a where a.users = :users and a.mixMasteringInfo = :mixMasteringInfo and a.createdAt = :now")
  Optional<MixMasteringRequest> findByMixMasteringInfoAndUsersAndCreatedAt(Users user,
      MixMasteringInfo mixMasteringInfo, LocalDateTime now);
}
