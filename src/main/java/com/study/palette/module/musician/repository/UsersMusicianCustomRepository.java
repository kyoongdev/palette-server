package com.study.palette.module.musician.repository;

import com.study.palette.common.dto.PageDto;
import com.study.palette.module.musician.dto.MusicianReviewResponseDto;
import com.study.palette.module.musician.dto.MusicianSellingResponseDto;
import com.study.palette.module.musician.dto.query.FindMusicianSellingQuery;
import com.study.palette.module.users.entity.Users;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsersMusicianCustomRepository {

  Page<MusicianSellingResponseDto> getMusicianSellingList(FindMusicianSellingQuery id, Pageable pageable);

  Long countBySellingCount(UUID id);

  Page<MusicianReviewResponseDto> getMusicianReviewList(PageDto query, Pageable pageable,
      Users users);

  Long countByNotApprovedCount(UUID id);
}
