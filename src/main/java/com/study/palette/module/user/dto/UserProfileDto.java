package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.Role;
import lombok.*;

@AllArgsConstructor
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

    // TODO 필요시 추가 가능
    @Builder
    public UserProfileDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

}
