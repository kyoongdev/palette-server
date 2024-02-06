package com.study.palette.module.mainHome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicianRankDto {

  @Schema(description = "뮤지션 Id")
  private String id;
  @Schema(description = "랭킹")
  private Long rankNumber;
  @Schema(description = "프로필 Url")
  private String profileUrl;
  @Schema(description = "뮤지션 이름")
  private String musicianName;

  public MusicianRankDto(byte[] id, Long rankNumber, String profileUrl, String musicianName) {
    this.id = UUID.nameUUIDFromBytes(id).toString();
    this.rankNumber = rankNumber;
    this.profileUrl = profileUrl;
    this.musicianName = musicianName;
  }
}
