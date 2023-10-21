package com.study.palette.module.albumArt.dto.review;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlbumArtReviewCreateRequestDto {
    public String id;
    public int rating;
    public String review;
    public LocalDate createdAt;
    public String userId;
    public AlbumArtInfo albumArtInfo;
}