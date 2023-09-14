package com.study.palette.module.artist.entity;

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
public class ArtistLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String serveFile;

    private int updateCount;

    private LocalDate period;

    private int draftCount;

    private boolean isAssign;

    private boolean isServeOriginFile;

    private boolean isOtherUseApproved;

    @Column(length = 50)
    private LocalDate createdAt;

    @Column(length = 50)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "artistInfoId")
    private ArtistInfo artistInfo;
}
