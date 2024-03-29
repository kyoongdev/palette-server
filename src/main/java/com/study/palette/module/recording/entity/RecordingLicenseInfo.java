package com.study.palette.module.recording.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.recording.dto.license.RecordingLicenseInfoCreateRequestDto;
import com.study.palette.module.recording.dto.license.RecordingLicenseInfoDto;
import com.study.palette.module.users.entity.Users;
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
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecordingLicenseInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private int licenseType;

  private int price;

  private int useTime;

  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "recordingInfoId")
  @JsonIgnore
  private RecordingInfo recordingInfo;

  public static RecordingLicenseInfo from(
      RecordingLicenseInfoCreateRequestDto recordingLicenseCreateDto, RecordingInfo recordingInfo) {
    return builder()
        .licenseType(recordingLicenseCreateDto.getLicenseType())
        .price(recordingLicenseCreateDto.getPrice())
        .useTime(recordingLicenseCreateDto.getUseTime())
        .users(recordingInfo.getUsers())
        .recordingInfo(recordingInfo)
        .build();
  }

  public static RecordingLicenseInfo from(
      RecordingLicenseInfoDto recordingLicenseInfoUpdateRequestDto,
      RecordingInfo recordingInfo) {
    return builder()
        .licenseType(recordingLicenseInfoUpdateRequestDto.getLicenseType().getLicenseType())
        .price(recordingLicenseInfoUpdateRequestDto.getPrice())
        .useTime(recordingLicenseInfoUpdateRequestDto.getUseTime())
        .users(recordingInfo.getUsers())
        .recordingInfo(recordingInfo)
        .build();
  }
}