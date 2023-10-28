package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtCreateRequestDto {
    @NotBlank(message = "서비스명을 입력해주세요.")
    public String serviceName;
    @NotBlank(message = "서비스 설명을 입력해주세요.")
    public String serviceExplain;
    public int salesType;
    @NotBlank(message = "수정관련 안내를 입력해주세요.")
    public String editInfo;
    @NotNull(message = "라이센스 정보를 입력해주세요.")
    public List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseInfo = new ArrayList<>();

    //TODO 파일 구현 후 추가
//    public List<MultipartFile> albumArtFiles = new ArrayList<>();
    public boolean serviceStatus;

    public AlbumArtInfo toEntity(User user) {
        return AlbumArtInfo.builder()
                .serviceName(this.getServiceName())
                .serviceExplain(this.getServiceExplain())
                .serviceStatus(this.isServiceStatus())
                .salesType(this.getSalesType())
                .editInfo(this.getEditInfo())
                .user(user)
                .albumArtLicenseInfo(new ArrayList<>())
//                .albumArtFile(new ArrayList<>()) //TODO 파일 구현 후 추가
                .build();
    }
}