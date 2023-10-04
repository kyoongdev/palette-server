package com.study.palette.module.albumArt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtCreateDto {
    @NotBlank(message = "서비스명을 입력해주세요.")
    private String serviceName;
    @NotBlank(message = "서비스 설명을 입력해주세요.")
    private String serviceExplain;
    @NotBlank(message = "판매 유형을 입력해주세요.")
    private int salesType;
    @NotBlank(message = "수정관련 안내를 입력해주세요.")
    private String editInfo;
    @NotBlank(message = "라이센스 정보를 입력해주세요.")
    private List<AlbumArtLicenseCreateDto> albumArtLicenseInfo = new ArrayList<>();

    //TODO 파일 구현 후 추가
//    private List<MultipartFile> albumArtFiles = new ArrayList<>();
    private boolean serviceStatus;
}