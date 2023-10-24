package com.study.palette.module.mixMastering.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private UUID id;
  //서비스 이름
  @Column(length = 50)
  private String serviceName;

  //작업 전 음원
  private String beforeJobMusic;
  //작업 후 음원
  private String afterJobMusic;
  //서비스 소개
  @Column(length = 1000)
  private String serviceExplain;
  //수정 관련 안내
  @Column(length = 1000)
  private String editInfo;
  //서비스 상태
  private boolean serviceStatus;
  //장르
  @Column(length = 1)
  private int genre;
  @CreationTimestamp
  private LocalDateTime createdAt;


  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY)
  private List<MixMasteringReview> mixMasteringReviews;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MixMasteringLicenseInfo> mixMasteringLicenseInfos;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<MixMasteringFile> mixMasteringFiles = new ArrayList<>();

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<MixMasteringContact> mixMasteringContacts;

  @ManyToOne()
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;
}
