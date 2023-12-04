package com.study.palette.module.mixMastering.entity;

import com.study.palette.module.mixMastering.dto.license.MixMasteringLicenseDto;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringLicenseInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  //라이센스 유형
  private int licenseType;
  //가격
  private int price;
  //제공 파일
  @Column(length = 20)
  private String servedType;
  //수정 횟수
  private int updateCount;
  //작업기간
  private int period;
  //시안 개수
  private int draftCount;
  //저작권 양도
  private boolean isAssign;
  //상업적 사용 가능 여부
  private boolean isUseCommercial;
  //원본 파일 제공
  private boolean isServeOriginFile;
  //응용 활용 여부
  private boolean isOtherUseApproved;

  @CreationTimestamp
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringInfo mixMasteringInfo;

  public static MixMasteringLicenseInfo from(
      MixMasteringLicenseDto mixMasteringLicenseCreateDto, MixMasteringInfo mixMasteringInfo) {
    return builder()
        .licenseType(mixMasteringLicenseCreateDto.getLicenseType().getLicenseType())
        .price(mixMasteringLicenseCreateDto.getPrice())
        .servedType(mixMasteringLicenseCreateDto.getServedType())
        .updateCount(mixMasteringLicenseCreateDto.getUpdateCount())
        .period(mixMasteringLicenseCreateDto.getPeriod())
        .draftCount(mixMasteringLicenseCreateDto.getDraftCount())
        .isAssign(mixMasteringLicenseCreateDto.isAssign())
        .isUseCommercial(mixMasteringLicenseCreateDto.isUseCommercial())
        .isServeOriginFile(mixMasteringLicenseCreateDto.isServeOriginFile())
        .isOtherUseApproved(mixMasteringLicenseCreateDto.isOtherUseApproved())
        .mixMasteringInfo(mixMasteringInfo)
        .build();
  }
}
