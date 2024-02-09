package com.study.palette.module.users.dto;


import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// users relation에 사용하는 dto(기본 유저)
@Data
@AllArgsConstructor
@NoArgsConstructor
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

  public CommonUserDto(Users users) {
    this.id = users.getId().toString();
    this.email = users.getEmail();
    this.name = users.getName();
    this.phone = users.getPhone();
    this.profileImage = users.getProfileImage();

  }
}
