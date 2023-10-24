package com.study.palette.module.mixMastering.dto.contact;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateMixMasteringContactDto {
  @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private Contact type;

  @Schema(description = "내용")
  private String content;


  public MixMasteringContact toEntity() {
    return MixMasteringContact.builder()
            .type(this.type.getContact())
            .content(this.content)
            .build();
  }
}
