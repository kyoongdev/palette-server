package com.study.palette.module.artist.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArtistReview {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int rating;

    @Column(length = 50)
    private String review;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artistInfoId")
    private ArtistInfo artistInfo;

}
