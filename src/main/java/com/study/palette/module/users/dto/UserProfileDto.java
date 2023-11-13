package com.study.palette.module.users.dto;

import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
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

  public UserProfileDto(Users users) {
    this.id = users.getId().toString();
    this.role = users.getRole();
    this.email = users.getEmail();
    this.name = users.getName();
    this.phone = users.getPhone();
    this.isAlarmAccept = users.isAlarmAccept();
    this.loginFailCount = users.getLoginFailCount();
    this.isLocked = users.isLocked();
    this.createdAt = users.getCreatedAt();
  }
}
