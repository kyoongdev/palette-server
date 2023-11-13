package com.study.palette.module.albumArt.dto.contact;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.albumArt.entity.AlbumArtContact;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtContactCreateDto {

  @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private Contact type;

  @Schema(description = "내용")
  private String content;


  public AlbumArtContact toEntity(AlbumArtInfo albumArtInfo) {
    return AlbumArtContact.builder()
        .type(this.type.getContact())
        .content(this.content)
        .albumArtInfo(albumArtInfo)
        .build();
  }
}
