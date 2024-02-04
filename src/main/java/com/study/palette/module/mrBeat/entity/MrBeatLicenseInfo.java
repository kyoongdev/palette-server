package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.LicenseType;
import com.study.palette.module.mrBeat.dto.license.CreateMrBeatLicenseInfoDto;
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
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MrBeatLicenseInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private LicenseType licenseType;

  private int price;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "mrBeatInfoId")
  @JsonIgnore
  private MrBeatInfo mrBeatInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public static MrBeatLicenseInfo from(CreateMrBeatLicenseInfoDto mrBeatLicenseInfoDto,
      MrBeatInfo mrBeatInfo) {
    return builder()
        .licenseType(LicenseType.findLicenseType(mrBeatLicenseInfoDto.getLicenseType()))
        .price(mrBeatLicenseInfoDto.getPrice())
        .createdAt(LocalDateTime.now())
        .mrBeatInfo(mrBeatInfo).build();
  }

}
