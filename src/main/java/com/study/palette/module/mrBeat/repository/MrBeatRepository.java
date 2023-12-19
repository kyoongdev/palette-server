package com.study.palette.module.mrBeat.repository;

import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MrBeatRepository extends JpaRepository<MrBeatInfo, String> , MrBeatCustomRepository{
  long countByServiceStatus(boolean registrationCompleted);
}
