package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
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
public class AlbumArtInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 50)
  private String serviceName;

  @Column(length = 1000)
  private String serviceExplain;

  private AlbumArtSaleType salesType;

  @Column(length = 1000)
  private String editInfo;

  // 판매여부
  @Column(columnDefinition = "boolean default false")
  private boolean isSelling;
  // 서비스 상태
  private ApprovalType approvalStatus;
  // 서비스 거부 사유
  private String refusalReason;

  @CreationTimestamp
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtFile> albumArtFile = new ArrayList<>();

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtLicenseInfo> albumArtLicenseInfo = new ArrayList<>();

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtContact> albumArtContact = new ArrayList<>();

  public void setAlbumArtLicenseInfo(List<AlbumArtLicenseInfo> albumArtLicenseInfos) {
    this.albumArtLicenseInfo = albumArtLicenseInfos;
  }

  public void setAlbumArtContact(List<AlbumArtContact> albumArtContacts) {
    this.albumArtContact = albumArtContacts;
  }

  public void setAlbumArtFiles(List<AlbumArtFile> albumArtFiles) {
    this.albumArtFile = albumArtFiles;
  }

  public void updateIsSelling(boolean isSelling) {
    this.isSelling = isSelling;
  }
}