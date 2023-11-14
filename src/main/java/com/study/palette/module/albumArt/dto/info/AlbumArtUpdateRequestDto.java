package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
import com.study.palette.module.albumArt.dto.file.AlbumArtFileDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtUpdateRequestDto {

  public String serviceName;
  public String serviceExplain;
  public String editInfo;
  public List<AlbumArtFileDto> albumArtFile;
  public List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfo;
  public List<AlbumArtContactDto> albumArtContact;

}