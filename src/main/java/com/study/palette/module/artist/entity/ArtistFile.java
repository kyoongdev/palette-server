package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isUse;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "artistInfoId")
    private ArtistInfo artistInfo;

    @Builder
    public ArtistFile(String originFileName, String uploadFileName, int uploadFileSize, String uploadFilePath, int fileType, String suffix, boolean isThumbnail, boolean isUse, LocalDate createdAt, String userId) {
        this.originFileName = originFileName;
        this.uploadFileName = uploadFileName;
        this.uploadFileSize = uploadFileSize;
        this.uploadFilePath = uploadFilePath;
        this.fileType = fileType;
        this.suffix = suffix;
        this.isThumbnail = isThumbnail;
        this.isUse = isUse;
        this.createdAt = createdAt;
        this.userId = userId;
    }
}
