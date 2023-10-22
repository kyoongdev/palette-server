package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AlbumArtLicenseInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private int licenseType;

    private int price;

    @Column(length = 20)
    private String servedFile;

    private int updateCount;

    private LocalDate period;

    private int draftCount;

    private boolean isAssign;

    private boolean isUseCommercial;

    private boolean isServeOriginFile;

    private boolean isOtherUseApproved;

    private LocalDate createAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    @JsonIgnore
    private AlbumArtInfo albumArtInfo;

    public static AlbumArtLicenseInfo from(AlbumArtLicenseInfoCreateRequestDto albumArtLicenseCreateDto, AlbumArtInfo albumArtInfo) {
        return builder()
                .licenseType(albumArtLicenseCreateDto.getLicenseType())
                .price(albumArtLicenseCreateDto.getPrice())
                .servedFile(albumArtLicenseCreateDto.getServedFile())
                .updateCount(albumArtLicenseCreateDto.getUpdateCount())
                .period(albumArtLicenseCreateDto.getPeriod())
                .draftCount(albumArtLicenseCreateDto.getDraftCount())
                .isAssign(albumArtLicenseCreateDto.isAssign())
                .isUseCommercial(albumArtLicenseCreateDto.isUseCommercial())
                .isServeOriginFile(albumArtLicenseCreateDto.isServeOriginFile())
                .isOtherUseApproved(albumArtLicenseCreateDto.isOtherUseApproved())
                .user(albumArtInfo.getUser())
                .albumArtInfo(albumArtInfo)
                .build();
    }

    public static AlbumArtLicenseInfo from(AlbumArtLicenseInfoWithIdDto albumArtLicenseInfoWithIdDto, AlbumArtInfo albumArtInfo) {
        return builder()
                .id(UUID.fromString(albumArtLicenseInfoWithIdDto.getId()))
                .licenseType(albumArtLicenseInfoWithIdDto.getLicenseType())
                .price(albumArtLicenseInfoWithIdDto.getPrice())
                .servedFile(albumArtLicenseInfoWithIdDto.getServedFile())
                .updateCount(albumArtLicenseInfoWithIdDto.getUpdateCount())
                .period(albumArtLicenseInfoWithIdDto.getPeriod())
                .draftCount(albumArtLicenseInfoWithIdDto.getDraftCount())
                .isAssign(albumArtLicenseInfoWithIdDto.isAssign())
                .isUseCommercial(albumArtLicenseInfoWithIdDto.isUseCommercial())
                .isServeOriginFile(albumArtLicenseInfoWithIdDto.isServeOriginFile())
                .isOtherUseApproved(albumArtLicenseInfoWithIdDto.isOtherUseApproved())
                .user(albumArtInfo.getUser())
                .albumArtInfo(albumArtInfo)
                .build();
    }
}
