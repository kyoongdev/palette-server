package com.study.palette.module.user.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MEMBER("ROLE_MEMBER", "일반회원"),
    MUSICIAN("ROLE_MUSICIAN", "뮤지션"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
