package com.study.palette.module.musician.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.musician.MusicianAuthorizedType;
import com.study.palette.common.enums.musician.MusicianGroupType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsersMusician {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @OneToMany(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<UsersMusicianPosition> usersMusicianPosition = new ArrayList<>();

  @OneToMany(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<UsersMusicianSns> usersMusicianSns = new ArrayList<>();

  @OneToOne(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private UsersMusicianFile usersMusicianFile;

  @OneToOne(mappedBy = "usersMusician", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private UsersMusicianAccount usersMusicianAccount;

  @Column(length = 100)
  private String stageName;

  @Column(length = 20)
  private String name;

  private MusicianGroupType groupType;

  private String profileImage;

  @Column(length = 3000)
  private String introduction;


  private MusicianAuthorizedType isAuthorized;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @OneToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "usersId")
  private Users users;

  public void setUsersMusicianPosition(List<UsersMusicianPosition> usersMusicianPosition) {
    this.usersMusicianPosition = usersMusicianPosition;
  }

  public void setUsersMusicianSns(List<UsersMusicianSns> usersMusicianSns) {
    this.usersMusicianSns = usersMusicianSns;
  }

  public void setUsersMusicianFile(UsersMusicianFile usersMusicianFile) {
    this.usersMusicianFile = usersMusicianFile;
  }

  public void setUsersMusicianAccount(UsersMusicianAccount usersMusicianAccount) {
    this.usersMusicianAccount = usersMusicianAccount;
  }

}
