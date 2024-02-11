package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.ArtistSalesType;
import com.study.palette.common.enums.musician.ApprovalType;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

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
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 50)
  private String serviceName;

  @Column(length = 1000)
  private String serviceInfo;

  @Column(length = 1000)
  private String editInfo;

  private ArtistSalesType salesType;

  // 판매여부
  @Column(columnDefinition = "boolean default false")
  private boolean isSelling;

  // 서비스 상태
  private ApprovalType approvalStatus;
  // 서비스 거부 사유
  private String refusalReason;

  @CreationTimestamp
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

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

  public void updateIsSelling(boolean isSelling) {
    this.isSelling = isSelling;
  }

}
