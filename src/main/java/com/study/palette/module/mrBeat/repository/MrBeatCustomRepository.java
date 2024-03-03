package com.study.palette.module.mrBeat.repository;

import com.study.palette.module.mrBeat.dto.FindMrBeatsQuery;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.service.MrBeatConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MrBeatCustomRepository {

  Page<MrBeatResponseDto> findAll(MrBeatConditions query, Pageable pageable);

  long count(MrBeatConditions query);


}
