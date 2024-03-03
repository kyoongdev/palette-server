package com.study.palette.module.mrBeat.dto;

import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MrBeatResponseDto {

  @Schema(description = "MrBeat ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "장르", example = "true")
  private int genre;
  @Schema(description = "분위기", example = "true")
  private int mood;
  @Schema(description = "서비스 타입", example = "true")
  private int salesType;

  private MrBeatMusicFileResponseDto mrBeatMusicFileResponseDto;

  @Schema(description = "파일경로", example = "파일경로")
  private String fileUrl;

  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "요청수", example = "10000")
  private long requestCount;

  @Schema(description = "유저 이름")
  private String usersName;

  @Schema(description = "유저 정보")
  private String profileImage;

  public MrBeatResponseDto(UUID id, String serviceName, MrBeatMoodType mood, MrBeatGenreType genre, MrBeatSalesType salesType,
      String fileUrl, String musicFileUrl, int minutes, int seconds, int price, long requestCount, String usersName, String profileImage) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.genre = genre.getCode();
    this.mood = mood.getCode();
    this.salesType = salesType.getCode();
    this.fileUrl = fileUrl;
    this.mrBeatMusicFileResponseDto = new MrBeatMusicFileResponseDto(musicFileUrl, minutes, seconds);
    this.price = price;
    this.requestCount = requestCount;
    this.usersName = usersName;
    this.profileImage = profileImage;

  }

}
