package com.study.palette.module.albumArt.entity;

import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AlbumArtInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(length = 50)
    private String serviceName;

    @Column(length = 1000)
    private String serviceExplain;

    private int salesType;

    @Column(length = 1000)
    private String editInfo;

    private boolean serviceStatus;

    @Column(columnDefinition = "datetime default now()")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AlbumArtFile> albumArtFile = new ArrayList<>();

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
    private List<AlbumArtReview> albumArtReview = new ArrayList<>();

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AlbumArtLicenseInfo> albumArtLicenseInfo = new ArrayList<>();

//    public void setAlbumArtFile(AlbumArtFile albumArtFile) {
//        this.albumArtFile.add(albumArtFile);
//        albumArtFile.setAlbumArtInfo(this);
//    }

    public void setAlbumArtLicenseInfo(AlbumArtLicenseInfo albumArtLicenseInfo) {
        this.albumArtLicenseInfo.add(albumArtLicenseInfo);
        albumArtLicenseInfo.setAlbumArtInfo(this);
    }
}
