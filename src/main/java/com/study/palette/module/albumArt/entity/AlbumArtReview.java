package com.study.palette.module.albumArt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.albumArt.dto.review.AlbumArtReviewCreateRequestDto;
import com.study.palette.module.user.entity.User;
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
public class AlbumArtReview {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    private int rating;

    @Column(length = 50)
    private String review;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    @JsonIgnore
    private AlbumArtInfo albumArtInfo;

    public static AlbumArtReview from(AlbumArtReviewCreateRequestDto albumArtReviewCreateDto, AlbumArtInfo albumArtInfo) {
        return builder()
                .rating(albumArtReviewCreateDto.getRating())
                .review(albumArtReviewCreateDto.getReview())
                .createdAt(albumArtReviewCreateDto.getCreatedAt())
                .user(albumArtInfo.getUser())
                .albumArtInfo(albumArtInfo)
                .build();
    }

    public void setAlbumArtInfo(AlbumArtInfo albumArtInfo) {
        this.albumArtInfo = albumArtInfo;
    }
}
