package com.study.palette.module.mixMastering.entity;

import com.study.palette.module.mixMastering.dto.file.MixMasteringFileDto;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String url;

  private boolean isThumbnail;

  @Column(length = 4)
  private String suffix;

  @CreationTimestamp
  @CreatedDate
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringInfo mixMasteringInfo;

  public static MixMasteringFile from(MixMasteringFileDto mixMasteringFileDto, MixMasteringInfo mixMasteringInfo) {
    return builder()
        .originFileName(mixMasteringFileDto.getOriginFileName())
        .uploadFileName(mixMasteringFileDto.getUploadFileName())
        .uploadFileSize(mixMasteringFileDto.getUploadFileSize())
        .url(mixMasteringFileDto.getUrl())
        .isThumbnail(mixMasteringFileDto.isThumbnail())
        .suffix(mixMasteringFileDto.getSuffix())
        .createdAt(mixMasteringFileDto.getCreatedAt())
        .mixMasteringInfo(mixMasteringInfo)
        .build();
  }

}
