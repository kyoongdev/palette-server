package com.study.palette.module.albumArt.entity;

import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parent;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AlbumArtFile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 256)
    private String originFileName;

    @Column(length = 256)
    private String uploadFileName;

    private int uploadFileSize;

    @Column(length = 256)
    private String upoladFilePath;

    private int fileMasterCode;

    @ManyToOne
    @JoinColumn(name = "albumArtId")
    private AlbumArtInfo albumArt;

    @Column(length = 4)
    private String suffix;

    private boolean isUse;

    private boolean isThumbnail;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    private AlbumArtInfo albumArtInfo;

    public static AlbumArtFile from(MultipartFile albumArtFiles, AlbumArtInfo albumArtInfo) {
        return new AlbumArtFile(albumArtFiles, albumArtInfo);
    }

    public AlbumArtFile(MultipartFile albumArtFile, AlbumArtInfo albumArtInfo) {
        this.originFileName = albumArtFile.getOriginalFilename();
        this.uploadFileName = PaletteUtils.generateUniqueName(albumArtFile.getOriginalFilename());
        this.uploadFileSize = (int) albumArtFile.getSize();
        this.upoladFilePath = "/upload"; //TODO 경로수정
        this.fileMasterCode = 1; //TODO 코드수정
        this.suffix = albumArtFile.getContentType();
        this.isUse = true;
        this.isThumbnail = false;
        this.createdAt = LocalDate.now();

        if (albumArtInfo != null) {
            setAlbumArt(albumArtInfo);
        }
    }

    public void setAlbumArt(AlbumArtInfo albumArtInfo) {

        if (this.albumArtInfo != null) {
            this.albumArtInfo.getAlbumArtFile().remove(this);
        }

        this.albumArtInfo = albumArtInfo;

        albumArtInfo.getAlbumArtFile().add(this);
    }
}
