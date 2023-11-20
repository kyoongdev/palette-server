package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ArtistFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String uploadFilePath;

  private int fileType;

  @Column(length = 4)
  private String suffix;

  private boolean isThumbnail;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean isUse;

  private LocalDate createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "artistInfoId")
  @JsonIgnore
  private ArtistInfo artistInfo;

  @Builder
  public ArtistFile(String originFileName, String uploadFileName, int uploadFileSize,
      String uploadFilePath, int fileType, String suffix, boolean isThumbnail, boolean isUse,
      LocalDate createdAt, Users users) {
    this.originFileName = originFileName;
    this.uploadFileName = uploadFileName;
    this.uploadFileSize = uploadFileSize;
    this.uploadFilePath = uploadFilePath;
    this.fileType = fileType;
    this.suffix = suffix;
    this.isThumbnail = isThumbnail;
    this.isUse = isUse;
    this.createdAt = createdAt;
    this.users = users;
  }
}
