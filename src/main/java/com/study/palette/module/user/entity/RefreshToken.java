package com.study.palette.module.user.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(columnDefinition = "varchar(255)")
  private String refreshToken;
  @Column(columnDefinition = "bigint")
  private long refreshExpirationTime;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
  @Column(columnDefinition = "datetime default now()")
  private Date issuedAt;


  @Builder
  public RefreshToken(String refreshToken, long refreshExpirationTime, Date issuedAt, User user) {
    this.refreshToken = refreshToken;
    this.refreshExpirationTime = refreshExpirationTime;
    this.user = user;
    this.issuedAt = issuedAt;
  }
}
