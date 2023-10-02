package com.study.palette.module.artist.entity;

import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArtistInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(length = 50)
    private String serviceName;

    @Column(length = 1000)
    private String serviceInfo;

    @Column(length = 1000)
    private String editInfo;

    @ManyToOne
    @JoinColumn(name = "salesType")
    private FilterInfo filterInfo;

    private int serviceStatus;          // boolean 타입으로 변경 필요

    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistFile> artistFile = new ArrayList<>();

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistLicenseInfo> artistLicenseInfo = new ArrayList<>();

    @OneToMany(mappedBy = "artistInfo", fetch = FetchType.LAZY)
    private List<ArtistReview> artistReview = new ArrayList<>();

    @Builder
    public ArtistInfo(String serviceName, String serviceInfo, String editInfo, FilterInfo filterInfo, int serviceStatus, LocalDate createAt, User user, List<ArtistFile> artistFile, List<ArtistLicenseInfo> artistLicenseInfo, List<ArtistReview> artistReview) {
        this.serviceName = serviceName;
        this.serviceInfo = serviceInfo;
        this.editInfo = editInfo;
        this.filterInfo = filterInfo;
        this.serviceStatus = serviceStatus;
        this.createAt = createAt;
        this.user = user;
        this.artistFile = artistFile;
        this.artistLicenseInfo = artistLicenseInfo;
        this.artistReview = artistReview;

    }

}
