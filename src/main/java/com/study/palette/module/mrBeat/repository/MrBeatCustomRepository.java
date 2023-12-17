package com.study.palette.module.mrBeat.repository;

import com.study.palette.module.mrBeat.dto.FindMrBeatsQuery;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MrBeatCustomRepository {

  Page<MrBeatResponseDto> findAll(FindMrBeatsQuery query, Pageable pageable);


}
