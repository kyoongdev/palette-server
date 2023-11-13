package com.study.palette.module.users.entity;

import java.time.LocalDate;
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
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class UsersFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private String id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String uploadFilePath;

  private int filterMasterCode;

  @Column(length = 4)
  private String suffix;

  private boolean isUse;

  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "usersId")
  private Users users;
}
