package com.study.palette.module.mainHome.repository;

import com.study.palette.module.mainHome.dto.MusicianRankDto;
import com.study.palette.module.mainHome.dto.query.FindTopMusicianQuery;
import java.util.List;

public interface MainHomeRepository {

  List<MusicianRankDto> getAlbumArtRanks(FindTopMusicianQuery query);

  List<MusicianRankDto> getArtistRanks(FindTopMusicianQuery query);

  List<MusicianRankDto> getRecordingRanks(FindTopMusicianQuery query);

  List<MusicianRankDto> getMrBeatRanks(FindTopMusicianQuery query);

  List<MusicianRankDto> getMixMasteringRanks(FindTopMusicianQuery query);
}
