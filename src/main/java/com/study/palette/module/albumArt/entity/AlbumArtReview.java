package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import java.math.BigDecimal;
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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AlbumArtReview {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private String id;

  private BigDecimal rating;

  @Column(length = 50)
  private String review;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "albumArtInfoId")
  @JsonIgnore
  private AlbumArtInfo albumArtInfo;

  public void setAlbumArtInfo(AlbumArtInfo albumArtInfo) {
    this.albumArtInfo = albumArtInfo;
  }
}
