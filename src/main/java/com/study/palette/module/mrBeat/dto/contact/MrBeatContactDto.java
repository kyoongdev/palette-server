package com.study.palette.module.mrBeat.dto.contact;

import com.study.palette.module.mrBeat.entity.MrBeatContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrBeatContactDto {

  @Schema(description = "아이디")
  private String id;

  @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private int type;

  @Schema(description = "내용")
  private String content;

  public MrBeatContactDto(MrBeatContact mrBeatContact) {
    this.id = mrBeatContact.getId().toString();
    this.type = mrBeatContact.getType().getContact();
    this.content = mrBeatContact.getContent();
  }

}
