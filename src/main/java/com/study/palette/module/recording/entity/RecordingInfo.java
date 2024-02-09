package com.study.palette.module.recording.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.recording.CityCode;
import com.study.palette.common.enums.recording.RegionCode;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecordingInfo {

  /*
   *
   * 스튜디오명
   * 스튜디오 주소 (도/특별시/광역시)
   * 스튜디오 주소 (시/군/구)
   * 녹음 엔지니어링 제공여부
   * 스튜디오 예약링크
   * 스튜디오 이용시간 별 가격 license
   * 서비스 설명
   */
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 100)
  private String serviceName;

  @Column(length = 100)
  private String studioName;

  @Column(length = 100)
  @Enumerated(EnumType.STRING)
  private RegionCode studioRegionCode;

  @Column(length = 100)
  @Enumerated(EnumType.STRING)
  private CityCode studioCityCode;

  //녹음 엔지니어링 제공 여부
  @Column(columnDefinition = "boolean default false")
  private boolean isRecordingEngineering;

  @Column(length = 1000)
  private String studioReservationUrl;

  @Column(length = 1000)
  private String serviceExplain;

  @Column(columnDefinition = "boolean default false")
  private boolean serviceStatus;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToMany(mappedBy = "recordingInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RecordingFile> recordingFile = new ArrayList<>();

  @OneToMany(mappedBy = "recordingInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RecordingLicenseInfo> recordingLicenseInfo = new ArrayList<>();

  public void setRecordingLicenseInfo(List<RecordingLicenseInfo> recordingLicenseInfos) {
    this.recordingLicenseInfo = recordingLicenseInfos;
  }

  public void setRecordingFile(List<RecordingFile> recordingFile) {
    this.recordingFile = recordingFile;
  }

  public void updateServiceStatus(boolean serviceStatus) {
    this.serviceStatus = serviceStatus;
  }
}