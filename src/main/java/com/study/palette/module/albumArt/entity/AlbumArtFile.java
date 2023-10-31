package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.entity.User;
import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AlbumArtFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private String id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String upoladFilePath;

  private int fileMasterCode;

  @ManyToOne
  @JoinColumn(name = "albumArtId")
  private AlbumArtInfo albumArt;

  @Column(length = 4)
  private String suffix;

  private boolean isUse;

  private boolean isThumbnail;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "albumArtInfoId")
  private AlbumArtInfo albumArtInfo;

  public static AlbumArtFile from(MultipartFile albumArtFiles, AlbumArtInfo albumArtInfo) {
    return new AlbumArtFile(albumArtFiles, albumArtInfo);
  }

  public AlbumArtFile(MultipartFile albumArtFile, AlbumArtInfo albumArtInfo) {
    this.originFileName = albumArtFile.getOriginalFilename();
    this.uploadFileName = PaletteUtils.generateUniqueName(albumArtFile.getOriginalFilename());
    this.uploadFileSize = (int) albumArtFile.getSize();
    this.upoladFilePath = "/upload"; //TODO 경로수정
    this.fileMasterCode = 1; //TODO 코드수정
    this.suffix = albumArtFile.getContentType();
    this.isUse = true;
    this.isThumbnail = false;
    this.createdAt = LocalDateTime.now();

    if (albumArtInfo != null) {
      setAlbumArt(albumArtInfo);
    }
  }

  public void setAlbumArt(AlbumArtInfo albumArtInfo) {
    if (this.albumArtInfo != null) {
      this.albumArtInfo.getAlbumArtFile().remove(this);
    }

    this.albumArtInfo = albumArtInfo;

    albumArtInfo.getAlbumArtFile().add(this);
  }


}
