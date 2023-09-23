package com.study.palette.module.albumArt.entity;

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
public class AlbumArtistInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 50)
    private String serviceName;

    @Column(length = 20)
    private String artistId;

    @Column(length = 1000)
    private String serviceExplain;

    private int salesType;

    @Column(length = 1000)
    private String editInfo;

    private boolean serviceStatus;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "albumArtistInfo", fetch = FetchType.LAZY)
    private List<AlbumArtistFile> albumArtistFile;

    @OneToMany(mappedBy = "albumArtistInfo", fetch = FetchType.LAZY)
    private List<AlbumArtistReview> albumArtistReview;

    @OneToMany(mappedBy = "albumArtistInfo", fetch = FetchType.LAZY)
    private List<AlbumArtistLicenseInfo> albumArtistLicenseInfo;

}
