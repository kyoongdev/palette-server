package com.study.palette.module.artist.dto.artistFile;


import lombok.Data;

@Data
public class CreateArtistFileDto {

    private String originFileName;


    private String uploadFileName;

    private int uploadFileSize;

    private String uploadFilePath;

    private int fileType;


    private String suffix;

    private boolean isThumbnail;

    private boolean isUse;


    private String targetId;


}
