package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class AlbumArtInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 50)
  private String serviceName;

  @Column(length = 1000)
  private String serviceExplain;

  private int salesType;

  @Column(length = 1000)
  private String editInfo;

  private boolean serviceStatus;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDateTime createdAt;

  //    @ManyToOne(cascade = CascadeType.PERSIST) TODO detached 오류 해결 필요
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtFile> albumArtFile = new ArrayList<>();

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtLicenseInfo> albumArtLicenseInfo = new ArrayList<>();

  @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AlbumArtContact> albumArtContact = new ArrayList<>();

  public void setAlbumArtLicenseInfo(List<AlbumArtLicenseInfo> albumArtLicenseInfos) {
    this.albumArtLicenseInfo = albumArtLicenseInfos;
  }

  public void setAlbumArtContact(List<AlbumArtContact> albumArtContacts) {
    this.albumArtContact = albumArtContacts;
  }

  public void setAlbumArtFiles(List<AlbumArtFile> albumArtFiles) {
    this.albumArtFile = albumArtFiles;
  }
}