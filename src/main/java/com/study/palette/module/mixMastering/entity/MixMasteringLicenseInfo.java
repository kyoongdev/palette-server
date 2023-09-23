package com.study.palette.module.mixMastering.entity;

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
public class MixMasteringLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String servedType;

    private int updateCount;

    private LocalDate period;

    private int draftCount;

    private boolean isAssign;

    private boolean isUseCommercial;

    private boolean isServeOriginFile;

    private boolean isOtherUseApproved;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "mixMasteringInfoId")
    private MixMasteringInfo mixMasteringInfo;


}
