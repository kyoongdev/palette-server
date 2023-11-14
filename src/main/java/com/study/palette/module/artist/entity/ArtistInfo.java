package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ArtistInfo {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(length = 50)
  private String serviceName;

  @Column(length = 1000)
  private String serviceInfo;

  @Column(length = 1000)
  private String editInfo;

  @ManyToOne
  @JoinColumn(name = "salesType")
  private FilterInfo filterInfo;

  private boolean serviceStatus;          //TODO: boolean 타입으로 변경 필요

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "usersId")
  private Users users;

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<ArtistFile> artistFile = new ArrayList<>();

  @OneToMany(mappedBy = "artistInfo",  cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<ArtistLicenseInfo> artistLicenseInfo = new ArrayList<>();

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<ArtistReview> artistReview = new ArrayList<>();

  public void setArtistFile(ArtistFile artistFileChild) {
    artistFile.add(artistFileChild);
    artistFileChild.setArtistInfo(this);
  }

  public void setUsers(Users usersInfo) {
    this.users = usersInfo;
  }

  public void setArtistLicenseInfo(ArtistLicenseInfo artistLicenseInfoChild) {
    artistLicenseInfo.add(artistLicenseInfoChild);
    artistLicenseInfoChild.setArtistInfo(this);
  }

  public void setArtistReview(ArtistReview artistReviewChild) {
    artistReview.add(artistReviewChild);
    artistReviewChild.setArtistInfo(this);
  }

  public void modifyArtistInfo(String serviceName, String serviceInfo, String editInfo, FilterInfo filterInfo, boolean serviceStatus, LocalDate createdAt, List<ArtistFile> artistFile, List<ArtistLicenseInfo> artistLicenseInfo) {
    this.serviceName = serviceName;
    this.serviceInfo = serviceInfo;
    this.editInfo = editInfo;
    this.filterInfo = filterInfo;
    this.serviceStatus = serviceStatus;
    this.createdAt = createdAt;
    this.users = users;
    this.artistFile = artistFile;
    this.artistLicenseInfo = artistLicenseInfo;
  }
}
