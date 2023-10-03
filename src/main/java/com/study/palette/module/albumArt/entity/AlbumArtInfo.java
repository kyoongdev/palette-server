package com.study.palette.module.albumArt.entity;

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
@Builder
public class AlbumArtInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 50)
    private String serviceName;

    @Column(length = 1000)
    private String serviceExplain;

    @ManyToOne
    @JoinColumn(name = "salesType")
    private FilterInfo filterInfo;

    @Column(length = 1000)
    private String editInfo;

    private boolean serviceStatus;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AlbumArtFile> albumArtFile = new ArrayList<>();

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
    private List<AlbumArtReview> albumArtReview = new ArrayList<>();

    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AlbumArtLicenseInfo> albumArtLicenseInfo = new ArrayList<>();

}
