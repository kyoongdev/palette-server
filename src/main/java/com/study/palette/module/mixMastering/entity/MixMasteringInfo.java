package com.study.palette.module.mixMastering.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MixMasteringInfo {

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

    @Column(length = 1000)
    private String editInfo;

    private boolean serviceStatus;

    private LocalDateTime createdAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "mixMasteringInfo")
    private List<MixMasteringReview> mixMasteringReview;

    @OneToMany(mappedBy = "mixMasteringInfo")
    private List<MixMasteringLicenseInfo> mixMasteringLicenseInfo;

    @OneToMany(mappedBy = "mixMasteringInfo")
    private List<MixMasteringFile> mixMasteringFile = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "mixMasteringInfoId")
    private MixMasteringGenre mixMasteringGenre;


}
