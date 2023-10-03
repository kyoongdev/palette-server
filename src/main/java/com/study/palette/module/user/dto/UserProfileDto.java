package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.Role;
import lombok.*;

@NoArgsConstructor
@Getter
public class UserProfileDto {

    private String id;
    private Role role;
    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean isAlarmAccept;
    private int loginFailCount;
    private boolean isLocked;
    private String createdAt;

    //builder 생성자
    @Builder
    public UserProfileDto(String id, Role role, String email, String password, String name, String phone, boolean isAlarmAccept, int loginFailCount, boolean isLocked, String createdAt) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.isAlarmAccept = isAlarmAccept;
        this.loginFailCount = loginFailCount;
        this.isLocked = isLocked;
        this.createdAt = createdAt;
    }
}
