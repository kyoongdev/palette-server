package com.study.palette.module.albumArt.dto;

import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtReview;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumArtCreateDto {
    private String serviceName;
    private String serviceExplain;
    private FilterInfo filterInfo;
    private String editInfo;
    private boolean serviceStatus;
    private LocalDate createdAt;
    private User user;
    private List<AlbumArtFile> albumArtFile;
    private List<AlbumArtReview> albumArtReview;
    private List<AlbumArtLicenseInfo> albumArtLicenseInfo;
//public class AlbumArtInfo {
//
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(length = 24)
//    private String id;
//
//    @Column(length = 50)
//    private String serviceName;
//
//    @Column(length = 1000)
//    private String serviceExplain;
//
//    @ManyToOne
//    @JoinColumn(name = "salesType")
//    private FilterInfo filterInfo;
//
//    @Column(length = 1000)
//    private String editInfo;
//
//    private boolean serviceStatus;
//
//    private LocalDate createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtFile> albumArtFile;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtReview> albumArtReview;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtLicenseInfo> albumArtLicenseInfo;

}