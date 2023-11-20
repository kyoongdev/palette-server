package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

  private int salesType;

  private boolean serviceStatus;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDate createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ArtistFile> artistFile = new ArrayList<>();

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ArtistLicenseInfo> artistLicenseInfo = new ArrayList<>();

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL)
  private List<ArtistReview> artistReview = new ArrayList<>();

  @OneToMany(mappedBy = "artistInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ArtistContact> artistContact = new ArrayList<>();

  public void setArtistFile(ArtistFile artistFileChild) {
    artistFile.add(artistFileChild);
    artistFileChild.setArtistInfo(this);
  }

}
