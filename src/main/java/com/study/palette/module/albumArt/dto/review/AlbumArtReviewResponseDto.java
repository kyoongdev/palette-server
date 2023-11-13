package com.study.palette.module.albumArt.dto.review;

import com.study.palette.module.albumArt.entity.AlbumArtReview;
import com.study.palette.module.users.dto.CommonUserDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AlbumArtReviewResponseDto {

  private String id;
  private int rating;
  private String review;
  private LocalDateTime createdAt;
  private CommonUserDto user;

  public AlbumArtReviewResponseDto(AlbumArtReview albumArtReview) {
    this.id = albumArtReview.getId().toString();
    this.rating = albumArtReview.getRating().intValue();
    this.review = albumArtReview.getReview();
    this.createdAt = albumArtReview.getCreatedAt();
    this.user = new CommonUserDto(albumArtReview.getUsers());
  }
}
