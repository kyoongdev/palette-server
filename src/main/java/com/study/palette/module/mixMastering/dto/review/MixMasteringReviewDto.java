package com.study.palette.module.mixMastering.dto.review;


import com.study.palette.module.mixMastering.entity.MixMasteringReview;
import com.study.palette.module.users.dto.CommonUserDto;
import java.time.LocalDate;
import lombok.Data;

@Data
public class MixMasteringReviewDto {

  private String id;

  private int rating;

  private String review;

  private LocalDate createdAt;

  private CommonUserDto user;

  public MixMasteringReviewDto(MixMasteringReview mixMasteringReview) {
    this.id = mixMasteringReview.getId().toString();
    this.rating = mixMasteringReview.getRating().intValue();
    this.review = mixMasteringReview.getReview();
    this.createdAt = mixMasteringReview.getCreatedAt();
    this.user = new CommonUserDto(mixMasteringReview.getUsers());
  }
}
