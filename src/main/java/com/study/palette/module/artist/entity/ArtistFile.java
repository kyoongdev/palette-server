package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.artist.dto.file.CreateArtistFileDto;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArtistFile {

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

  @Column(length = 4)
  private String suffix;

  private boolean isThumbnail;

  private boolean isUse;

  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "artistInfoId")
  @JsonIgnore
  private ArtistInfo artistInfo;

  public static ArtistFile from(CreateArtistFileDto artistFile, ArtistInfo aritstInfo) {
    return ArtistFile.builder()
        .url(artistFile.getUrl())
        .originFileName(artistFile.getOriginFileName())
        .uploadFileName(artistFile.getUploadFileName())
        .uploadFileSize(artistFile.getUploadFileSize())
        .suffix(artistFile.getSuffix())
        .isThumbnail(artistFile.isThumbnail())
        .isUse(true)
        .createdAt(LocalDateTime.now())
        .artistInfo(aritstInfo)
        .users(aritstInfo.getUsers())
        .build();
  }
}
