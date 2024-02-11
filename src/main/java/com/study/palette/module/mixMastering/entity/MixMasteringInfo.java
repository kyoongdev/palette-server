package com.study.palette.module.mixMastering.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.common.enums.musician.ApprovalType;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MixMasteringInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
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
  // 판매여부
  @Column(columnDefinition = "boolean default false")
  private boolean isSelling;
  // 서비스 상태
  private ApprovalType approvalStatus;
  // 서비스 거부 사유
  private String refusalReason;
  //장르
  @Column(length = 1)
  private MixMasteringGenre genre;
  @CreationTimestamp
  private LocalDateTime createdAt;


  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY)
  private List<MixMasteringReview> mixMasteringReviews;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MixMasteringLicenseInfo> mixMasteringLicenseInfos;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MixMasteringFile> mixMasteringFiles = new ArrayList<>();

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MixMasteringContact> mixMasteringContacts;

  @ManyToOne()
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public void updateIsSelling(boolean isSelling) {
    this.isSelling = isSelling;
  }
}
