package com.study.palette.module.mixMastering.dto;


import com.study.palette.module.mixMastering.entity.MixMasteringReview;
import com.study.palette.module.user.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MixMasteringReviewDto {

  private String id;

  private int rating;

  private String review;

  private LocalDate createdAt;

  private User user;

  public MixMasteringReviewDto(MixMasteringReview mixMasteringReview) {
    this.id = mixMasteringReview.getId().toString();
    this.rating = mixMasteringReview.getRating().intValue();
    this.review = mixMasteringReview.getReview();
    this.createdAt = mixMasteringReview.getCreatedAt();
    this.user = mixMasteringReview.getUser();

  }
}
