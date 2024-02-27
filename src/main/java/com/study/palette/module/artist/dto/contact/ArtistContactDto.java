package com.study.palette.module.artist.dto.contact;

import com.study.palette.module.artist.entity.ArtistContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistContactDto {
  @Schema(description = "아이디")
  private String id;

  @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private int type;

  @Schema(description = "내용")
  private String content;

  public ArtistContactDto(ArtistContact artistContact) {
    this.id = artistContact.getId().toString();
    this.type = artistContact.getType().getContact();
    this.content = artistContact.getContent();
  }

}
