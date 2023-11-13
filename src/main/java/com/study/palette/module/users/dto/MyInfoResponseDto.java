package com.study.palette.module.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class MyInfoResponseDto {

  @Schema(description = "유저 ID")
  @JsonIgnore
  private String id;

  @Schema(description = "유저 권한")
  @JsonIgnore
  private Role role;

  @Schema(description = "프로필 이미지")
  private String profileImage;

  @Schema(description = "닉네임")
  private String nickname;

//  @Schema(description = "소셜 로그인한 소셜 타입 식별자(Naver, Google, Kakao)")
//  private SocialType socialType;    //소셜 로그인한 소셜 타입 식별자(Naver, Google, Kakao)
//  @Schema(description = "로그인한 소셜 타입 식별자(일반 로그인인 경우 null)")
//  private String socialId;          // 로그인한 소셜 타입 식별자(일반 로그인인 경우 null)
//  @Schema(description = "프로필 이미지 링크")
//  private String profileImageUrl;
//  @Schema(description = "이메일")
//  private String email;
//  @Schema(description = "이름")
//  private String name;
//  @Schema(description = "휴대폰 번호")
//  private String phone;
//  @Schema(description = "알림 수신 여부")
//  private boolean isAlarmAccept;
//  @Schema(description = "로그인 실패 횟수")
//  private int loginFailCount;
//  @Schema(description = "계정 잠김 여부")
//  private boolean isLocked;
//  @Schema(description = "계정 생성 일자")
//  private LocalDateTime createdAt;
//  @Schema(description = "계정 삭제 일자")
//  private LocalDateTime deletedAt;
//  private List<UserFollowing> userFolloing;
//  private List<UserFollower> userFollower;

}
