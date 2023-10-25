package com.study.palette.module.albumArt.dto.review;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtReview;
import com.study.palette.module.mixMastering.entity.MixMasteringReview;
import com.study.palette.module.user.dto.CommonUserDto;
import com.study.palette.module.user.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlbumArtReviewResponseDto {
    private String id;
    private int rating;
    private String review;
    private LocalDate createdAt;
    private CommonUserDto user;

    public AlbumArtReviewResponseDto(AlbumArtReview albumArtReview) {
        this.id = albumArtReview.getId().toString();
        this.rating = albumArtReview.getRating().intValue();
        this.review = albumArtReview.getReview();
        this.createdAt = albumArtReview.getCreatedAt();
        this.user = new CommonUserDto(albumArtReview.getUser());
    }
}
