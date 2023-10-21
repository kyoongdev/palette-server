package com.study.palette.module.mixMastering.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  @Column(length = 50)
  private String serviceName;


  @Column(length = 1000)
  private String serviceExplain;

  @Column(length = 1000)
  private String editInfo;

  private boolean serviceStatus;

  private LocalDateTime createdAt;


  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY)
  private List<MixMasteringReview> mixMasteringReview;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MixMasteringLicenseInfo> mixMasteringLicenseInfo;

  @OneToMany(mappedBy = "mixMasteringInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<MixMasteringFile> mixMasteringFile = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringGenre mixMasteringGenre;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;


}
