package com.study.palette.module.user.dto;


import com.study.palette.module.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// user relation에 사용하는 dto(기본 유저)
@Data
public class CommonUserDto {
  @Schema(description = "아이디")
  private String id;
  @Schema(description = "이메일")
  private String email;
  @Schema(description = "이름")
  private String name;
  @Schema(description = "핸드폰 번호")
  private String phone;
  @Schema(description = "프로필 이미지", nullable = true)
  private String profileImage;

  public CommonUserDto(User user) {
    this.id = user.getId().toString();
    this.email = user.getEmail();
    this.name = user.getName();
    this.phone = user.getPhone();
    this.profileImage = user.getProfileImage();

  }
}
