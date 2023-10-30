package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtUpdateReqeustDto {
    public String serviceName;
    public String serviceExplain;
    public String editInfo;
//    public List<MultipartFile> files; TODO 파일 구현 후 추가
//    public List<AlbumArtFile> albumArtFile; TODO 파일 구현 후 추가
    public List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfo;
    public List<AlbumArtContactDto> albumArtContact;

}