package com.study.palette.module.artist.entity;

import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ArtistInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 50)
    private String serviceName;

    @Column(length = 1000)
    private String serviceInfo;

    @Column(length = 1000)
    private String editInfo;

    private int salesType;

    private int serviceStatus;          // boolean 타입으로 변경 필요

    private LocalDate createAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistFile> artistFile;

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistLicenseInfo> artistLicenseInfo;

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistReview> artistReview;



}
