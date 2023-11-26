package com.study.palette.module.artist.dto;

import com.study.palette.common.enums.Contact;
import io.swagger.v3.oas.annotations.media.Schema;

public class ArtistContactDto {

  @Schema(description = "아이디")
  private String id;

  @Schema(description = "종류", example = "1 : 전화번호, 2 : 이메일, 3 : 카카오톡, 4 : 인스타그램, 5 : 기타")
  private Contact type;

  @Schema(description = "내용")
  private String content;

}
