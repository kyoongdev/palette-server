package com.study.palette.module.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {

  @Schema(description = "닉네임", example = "nickname")
  private String nickname;

  @Schema(description = "프로필 이미지", example = "https://pallete-file.s3.ap-northeast-2.amazonaws.com/7c540940-8dcb-43e7-8a58-91c6066c860d.png")
  private String profileImage;

  @Builder
  public UserUpdateDto(String nickname, String profileImage) {
    this.nickname = nickname;
    this.profileImage = profileImage;
  }
}
