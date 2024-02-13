package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class UsersMusicianFile {
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

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean isUse;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToOne
  @JoinColumn(name = "usersMusicianId")
  @JsonIgnore
  private UsersMusician usersMusician;

}
