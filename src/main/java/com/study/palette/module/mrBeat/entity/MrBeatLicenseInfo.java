package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.mrBeat.dto.CreateMrBeatLicenseInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MrBeatLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String servedFile;

//    @ColumnDefault("true")
//    private boolean isMakeNewSong;
//
//    @ColumnDefault("true")
//    private boolean isUseCommercial;
//
//    @ColumnDefault("true")
//    private boolean isBackgroundPlay;
//
//    @ColumnDefault("true")
//    private boolean isMakeMusicVideo;
//
//    @ColumnDefault("false")
//    private boolean isSellShare;
//
//    private boolean isArrangement;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "mrBeatInfoId")
    @JsonIgnore
    private MrBeatInfo mrBeatInfo;

    public static MrBeatLicenseInfo from(CreateMrBeatLicenseInfoDto mrBeatLicenseInfoDto, MrBeatInfo mrBeatInfo) {
        return builder()
                .licenseType(mrBeatLicenseInfoDto.getLicenseType())
                .price(mrBeatLicenseInfoDto.getPrice())
                .mrBeatInfo(mrBeatInfo).build();
    }

}
