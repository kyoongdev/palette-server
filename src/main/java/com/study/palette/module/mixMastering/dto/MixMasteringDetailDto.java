package com.study.palette.module.mixMastering.dto;

import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.module.mixMastering.dto.file.MixMasteringFileDto;
import com.study.palette.module.mixMastering.dto.license.MixMasteringLicenseDto;
import com.study.palette.module.mixMastering.dto.review.MixMasteringReviewDto;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.user.dto.CommonUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MixMasteringDetailDto {
  @Schema(name = "아이디")
  private String id;
  @Schema(name = "서비스 제목")
  private String serviceName;
  @Schema(name = "서비스 설명")
  private String serviceExplain;
  @Schema(name = "수정 관련 안내")
  private String editInfo;
  @Schema(name = "서비스 상태")
  private boolean sericeStatus;
  @Schema(name = "작업 전 음원")
  private String beforeJobMusic;
  @Schema(name = "작업 후 음원")
  private String afterJobMusic;
  @Schema(name = "장르")
  private MixMasteringGenre genre;
  @Schema(name = "생성일자")
  private LocalDateTime createdAt;
  private CommonUserDto user;
  @Schema(name = "리뷰")
  private List<MixMasteringReviewDto> reviews;
  @Schema(name = "라이센스 정보")
  private List<MixMasteringLicenseDto> mixMasteringLicenseInfos;

  @Schema(name = "이미지들")
  private List<MixMasteringFileDto> mixMasteringFile;


  public MixMasteringDetailDto(MixMasteringInfo mixMasteringInfo) {
    this.id = mixMasteringInfo.getId().toString();
    this.serviceName = mixMasteringInfo.getServiceName();
    this.serviceExplain = mixMasteringInfo.getServiceExplain();
    this.editInfo = mixMasteringInfo.getEditInfo();
    this.beforeJobMusic = mixMasteringInfo.getBeforeJobMusic();
    this.afterJobMusic = mixMasteringInfo.getAfterJobMusic();
    this.sericeStatus = mixMasteringInfo.isServiceStatus();
    this.genre = MixMasteringGenre.findMixMasteringGenre(mixMasteringInfo.getGenre());
    this.createdAt = mixMasteringInfo.getCreatedAt();
    this.user = new CommonUserDto(mixMasteringInfo.getUser());
    this.reviews = mixMasteringInfo.getMixMasteringReviews().stream().map(MixMasteringReviewDto::new).collect(Collectors.toList());
    this.mixMasteringLicenseInfos = mixMasteringInfo.getMixMasteringLicenseInfos().stream().map(MixMasteringLicenseDto::new).collect(Collectors.toList());
    this.mixMasteringFile = mixMasteringInfo.getMixMasteringFile().stream().map(MixMasteringFileDto::new).collect(Collectors.toList());
  }
}
