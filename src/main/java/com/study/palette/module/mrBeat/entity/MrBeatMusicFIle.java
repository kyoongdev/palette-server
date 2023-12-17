package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class MrBeatMusicFIle {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  private String url;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  private int fileType;

  @Column(length = 4)
  private String suffix;

  @Column(columnDefinition = "tinyint(1) default 1")
  private boolean isUse;

  private LocalDate createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @OneToOne
  @JoinColumn(name = "mrBeatInfo")
  @JsonIgnore
  private MrBeatInfo mrBeatInfo;

}
