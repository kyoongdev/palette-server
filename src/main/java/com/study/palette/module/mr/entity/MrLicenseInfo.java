package com.study.palette.module.mr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MrLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String servedFile;

    private boolean isMakeNewSong;

    private boolean isUseCommercial;

    private boolean isBackgroundPlay;

    private boolean isMakeMusicVideo;

    private boolean isSellShare;

    private boolean isArrangement;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "mrInfoId")
    private MrInfo mrInfo;

}
