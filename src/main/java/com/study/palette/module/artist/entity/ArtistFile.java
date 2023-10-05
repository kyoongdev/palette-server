package com.study.palette.module.artist.entity;

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
public class ArtistFile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(length = 256)
    private String originFileName;

    @Column(length = 256)
    private String uploadFileName;

    private int uploadFileSize;

    @Column(length = 256)
    private String uploadFilePath;

    private int fileType;

    @Column(length = 4)
    private String suffix;

    private boolean isThumbnail;

    private boolean isUse;

    private LocalDate createdAt;

    @Column(length = 24)
    private String targetId;

    @Column(length = 24)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "artistInfoId")
    private ArtistInfo artistInfo;
}
