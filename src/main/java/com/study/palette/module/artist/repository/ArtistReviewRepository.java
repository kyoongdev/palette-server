package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.entity.ArtistReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistReviewRepository extends JpaRepository<ArtistReview, String> {


}
