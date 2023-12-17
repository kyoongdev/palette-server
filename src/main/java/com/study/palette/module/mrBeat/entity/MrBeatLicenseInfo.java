package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.mrBeat.dto.CreateMrBeatLicenseInfoDto;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
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
  private String id;

  private int licenseType;

  private int price;

  @Column(length = 20)
  private String servedFile;

//    @ColumnDefault("true")
//    private boolean isMakeNewSong;
//
//    @ColumnDefault("true")
//    private boolean isUseCommercial;
//
//    @ColumnDefault("true")
//    private boolean isBackgroundPlay;
//
//    @ColumnDefault("true")
//    private boolean isMakeMusicVideo;
//
//    @ColumnDefault("false")
//    private boolean isSellShare;
//
//    private boolean isArrangement;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDate createdAt;

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
        .licenseType(mrBeatLicenseInfoDto.getLicenseType())
        .price(mrBeatLicenseInfoDto.getPrice())
        .mrBeatInfo(mrBeatInfo).build();
  }

}
