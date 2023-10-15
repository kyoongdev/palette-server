package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String serveFile; ;

    private int updateCount;

    private int period;

    private int draftCount;

    private boolean isAssign;

    private boolean isServeOriginFile;

    private boolean isOtherUseApproved;

    private boolean isUseCommerical;

    @Column(length = 50)
    private LocalDate createdAt;

    @Column(length = 50)
    private String userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artistInfoId")
    private ArtistInfo artistInfo;

    @Builder
    public ArtistLicenseInfo(int licenseType, int price, String serveFile, int updateCount, int period, int draftCount, boolean isAssign, boolean isServeOriginFile, boolean isOtherUseApproved, boolean isUseCommerical, LocalDate createdAt, String userId, ArtistInfo artistInfo) {
        this.licenseType = licenseType;
        this.price = price;
        this.serveFile = serveFile;
        this.updateCount = updateCount;
        this.period = period;
        this.draftCount = draftCount;
        this.isAssign = isAssign;
        this.isServeOriginFile = isServeOriginFile;
        this.isOtherUseApproved = isOtherUseApproved;
        this.isUseCommerical = isUseCommerical;
        this.createdAt = createdAt;
        this.userId = userId;
        this.artistInfo = artistInfo;
    }
}
