package com.study.palette.module.albumArt.dto.file;

import com.study.palette.module.albumArt.entity.AlbumArtFile;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlbumArtFileResponseDto {
    private String id;
    private String originFileName;
    private String uploadFileName;
    private int uploadfileSize;
    private String suffix;
    private boolean isThumbnail;
    private LocalDateTime createdAt;

    public AlbumArtFileResponseDto(AlbumArtFile albumArtFile) {
        this.id = albumArtFile.getId().toString();
        this.originFileName = albumArtFile.getOriginFileName();
        this.uploadFileName = albumArtFile.getUploadFileName();
        this.uploadfileSize = albumArtFile.getUploadFileSize();
        this.suffix = albumArtFile.getSuffix();
        this.isThumbnail = albumArtFile.isThumbnail();
        this.createdAt = albumArtFile.getCreatedAt();
    }
}
