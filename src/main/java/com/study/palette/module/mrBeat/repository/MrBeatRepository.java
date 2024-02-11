package com.study.palette.module.mrBeat.repository;

import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MrBeatRepository extends JpaRepository<MrBeatInfo, String> , MrBeatCustomRepository{
  long countByIsSelling(boolean registrationCompleted);

  Optional<MrBeatInfo> findById(UUID id);

  Optional<MrBeatInfo> deleteById(UUID id);


}
