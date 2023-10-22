package com.study.palette.module.mixMastering.dto.contact;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MixMasteringContactDto {
  @Schema(name = "아이디")
  private String id;

  @Schema(name = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private Contact type;

  @Schema(name = "내용")
  private String content;

  public MixMasteringContactDto(MixMasteringContact mixMasteringContact) {
    this.id = mixMasteringContact.getId().toString();
    this.type = Contact.findContact(mixMasteringContact.getType());
    this.content = mixMasteringContact.getContent();
  }
}
