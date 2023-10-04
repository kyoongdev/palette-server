package com.study.palette.module.albumArt.entity;

import com.study.palette.module.albumArt.dto.AlbumArtLicenseCreateDto;
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    private AlbumArtInfo albumArtInfo;

    public static AlbumArtLicenseInfo from(AlbumArtLicenseCreateDto albumArtLicenseCreateDto, AlbumArtInfo albumArtInfo) {
        AlbumArtLicenseInfo albumArtLicenseInfo = builder()
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
        albumArtInfo.setAlbumArtLicenseInfo(albumArtLicenseInfo);
        return albumArtLicenseInfo;
    }

    public void setAlbumArtInfo(AlbumArtInfo albumArtInfo) {
        this.albumArtInfo = albumArtInfo;
    }

}
