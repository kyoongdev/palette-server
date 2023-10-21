package com.study.palette.module.mixMastering.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private UUID id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String upoladFilePath;


  @Column(length = 256)
  private String uploadFilePath;

  private int fileMasterCode;

  private int fileType;

  private boolean isThumbnail;


  @Column(length = 4)
  private String suffix;

  private boolean isUse;

  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;


  @ManyToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringInfo mixMasteringInfo;


  public static MixMasteringFile from(MultipartFile mixMasteringFile, MixMasteringInfo mixMasteringInfo) {
    return new MixMasteringFile(mixMasteringFile, mixMasteringInfo);
  }

  public MixMasteringFile(MultipartFile mixMasteringFile, MixMasteringInfo mixMasteringInfo) {
    this.originFileName = mixMasteringFile.getOriginalFilename();
    this.uploadFileName = PaletteUtils.generateUniqueName(mixMasteringFile.getOriginalFilename());
    this.uploadFileSize = (int) mixMasteringFile.getSize();
    this.upoladFilePath = "/upload";
    this.fileMasterCode = 1;
    this.suffix = mixMasteringFile.getContentType();
    this.isUse = true;
    this.isThumbnail = false;
    this.createdAt = LocalDate.now();

    if (mixMasteringInfo != null) {
      setMixMatering(mixMasteringInfo);
    }
  }

  public void setMixMatering(MixMasteringInfo mixMasteringInfo) {
    if (this.mixMasteringInfo != null) {
      this.mixMasteringInfo.getMixMasteringFile().remove(this);
    }
    this.mixMasteringInfo = mixMasteringInfo;

    mixMasteringInfo.getMixMasteringFile().add(this);

  }

}
