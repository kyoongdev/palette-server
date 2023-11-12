package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserProfileDto {

  private String id;
  private Role role;
  private String email;
  private String name;
  private String phone;
  private boolean isAlarmAccept;
  private int loginFailCount;
  private boolean isLocked;
  private LocalDateTime createdAt;

  public UserProfileDto(User user) {
    this.id = user.getId().toString();
    this.role = user.getRole();
    this.email = user.getEmail();
    this.name = user.getName();
    this.phone = user.getPhone();
    this.isAlarmAccept = user.isAlarmAccept();
    this.loginFailCount = user.getLoginFailCount();
    this.isLocked = user.isLocked();
    this.createdAt = user.getCreatedAt();
  }
}
