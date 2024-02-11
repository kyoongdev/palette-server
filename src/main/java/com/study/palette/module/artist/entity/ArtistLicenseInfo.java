package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.LicenseType;
import com.study.palette.module.artist.dto.license.CreateArtistLicenseDto;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistLicenseInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private LicenseType licenseType;

  private int price;

  @Column(length = 20)
  private String serveFile;

  private int updateCount;

  private int period;

  private int draftCount;

  private boolean isAssign;

  private boolean isServeOriginFile;

  private boolean isOtherUseApproved;

  private boolean isUseCommerical;

  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "artistInfoId")
  @JsonIgnore
  private ArtistInfo artistInfo;

  @Builder
  public ArtistLicenseInfo(int licenseType, int price, String serveFile, int updateCount,
      int period, int draftCount, boolean isAssign, boolean isServeOriginFile,
      boolean isOtherUseApproved, boolean isUseCommerical, LocalDateTime createdAt, Users users,
      ArtistInfo artistInfo) {
    this.licenseType = LicenseType.findLicenseType(licenseType);
    this.price = price;
    this.serveFile = serveFile;
    this.updateCount = updateCount;
    this.period = period;
    this.draftCount = draftCount;
    this.isAssign = isAssign;
    this.isServeOriginFile = isServeOriginFile;
    this.isOtherUseApproved = isOtherUseApproved;
    this.isUseCommerical = isUseCommerical;
    this.createdAt = createdAt;
    this.users = users;
    this.artistInfo = artistInfo;
  }

  public static ArtistLicenseInfo from(CreateArtistLicenseDto artistLicenseInfoDto,
      ArtistInfo artistInfo) {
    return builder()
        .licenseType(artistLicenseInfoDto.getLicenseType())
        .price(artistLicenseInfoDto.getPrice())
        .serveFile(artistLicenseInfoDto.getServeFile())
        .updateCount(artistLicenseInfoDto.getUpdateCount())
        .period(artistLicenseInfoDto.getPeriod())
        .draftCount(artistLicenseInfoDto.getDraftCount())
        .isAssign(artistLicenseInfoDto.isAssign())
        .isServeOriginFile(artistLicenseInfoDto.isServeOriginFile())
        .isOtherUseApproved(artistLicenseInfoDto.isServeOriginFile())
        .isUseCommerical(artistLicenseInfoDto.isUseCommerical())
        .artistInfo(artistInfo)
        .users(artistInfo.getUsers()).build();
  }
}
