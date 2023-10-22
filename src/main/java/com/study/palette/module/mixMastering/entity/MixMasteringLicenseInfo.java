package com.study.palette.module.mixMastering.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringLicenseInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
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

}
