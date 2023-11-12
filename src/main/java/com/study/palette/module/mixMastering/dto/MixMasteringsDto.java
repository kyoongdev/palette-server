package com.study.palette.module.mixMastering.dto;

import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import java.util.UUID;
import lombok.Data;

@Data
public class MixMasteringsDto {

  private String id;
  private String serviceName;
  private String artistName;
  private MixMasteringGenre genre;
  private String beforeJobMusic;
  private String afterJobMusic;
  private int price;
  private String thumbnailUrl;
  private Long requestCount;

  /*
   * 네임
   * 아티스트
   * 장르
   * 작업전파일
   * 작업후파일
   * 가격
   * 썸네일
   * */
  public MixMasteringsDto(UUID id, String serviceName, String artistName,
      MixMasteringGenre genre, String beforeJobMusic, String afterJobMusic, int price,
      String thumbnailUrl, Long requestCount) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.artistName = artistName;
    this.genre = genre;
    this.beforeJobMusic = beforeJobMusic;
    this.afterJobMusic = afterJobMusic;
    this.price = price;
    this.thumbnailUrl = thumbnailUrl;
    this.requestCount = requestCount;
  }
}
