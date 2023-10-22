package com.study.palette.module.mixMastering.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(length = 24)
  private UUID id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String url;


  private boolean isThumbnail;


  @Column(length = 4)
  private String suffix;


  @CreationTimestamp
  @CreatedDate
  private LocalDate createdAt;


  @ManyToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringInfo mixMasteringInfo;


}
