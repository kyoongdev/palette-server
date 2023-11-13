package com.study.palette.module.user.entity;

import com.study.palette.module.musician.entity.UserMusician;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Getter
@Setter     // TODO dto <--> entity 전환을 copyProperty 로 하기위해 추가함--> 추후 좀더 다른 방법 알아보면 될듯
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  private SocialType socialType;    //소셜 로그인한 소셜 타입 식별자(Naver, Google, Kakao)

  private String socialId;          // 로그인한 소셜 타입 식별자(일반 로그인인 경우 null)

  @Column(columnDefinition = "varchar(255)")
  private String email;

  @Column(columnDefinition = "varchar(255)")
  private String password;

  @Column(columnDefinition = "varchar(100)")
  private String name;

  @Column(columnDefinition = "varchar(100)")
  private String nickname;

  @Column(nullable = true)
  private String profileImage;

  @Column(columnDefinition = "char(11)")
  private String phone;

  @Column(columnDefinition = "boolean default false")
  private boolean isAlarmAccept;

  @Column(columnDefinition = "int default 0")
  private int loginFailCount;

  @Column(columnDefinition = "boolean default false")
  private boolean isLocked;

  @Column(columnDefinition = "datetime default now()")
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(columnDefinition = "datetime")
  private LocalDateTime deletedAt;

  public void updateLoginFailCount(int loginFailCount) {
    this.loginFailCount = loginFailCount;
  }

  public void updateIsLocked(boolean isLocked) {
    this.isLocked = isLocked;
  }

  public void generateDeletedAt() {
    this.deletedAt = LocalDateTime.now();
  }

  public void resetDeletedAt() {
    this.deletedAt = null;
  }

  @Builder
  public User(Role role, String email, String password, String name, String phone,
      boolean isAlarmAccept, int loginFailCount, boolean isLocked, LocalDateTime createdAt,
      SocialType socialType, String socialId, UserMusician userMusician) {
    this.role = role;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
    this.isAlarmAccept = isAlarmAccept;
    this.socialType = socialType;
    this.socialId = socialId;
    this.userMusician = userMusician;
  }

  @OneToOne
  @JoinColumn(name = "userMusician")
  private UserMusician userMusician;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserFile> userFile = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserFollowing> userFolloing = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserFollower> userFollower = new ArrayList<>();

}
