package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.service.ArtistConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistCustomRepository {

  Page<ArtistResponseDto> findAll(ArtistConditions query, Pageable pageable);

}
