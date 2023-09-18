package com.study.palette.module.user.entity;

import com.study.palette.module.user.entity.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
//    @Type(type = "org.hibernate.type.BinaryType")
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(columnDefinition = "varchar(255)")
    String email;
    @Column(columnDefinition = "varchar(255)")
    String password;
    @Column(columnDefinition = "varchar(100)")
    String name;
    @Column(columnDefinition = "varchar(100)")
    String phone;
    @Column(columnDefinition = "boolean default false")
    boolean isAlarmAccept;
    @Column(columnDefinition = "int default 0")
    int loginFailCount;
    @Column(columnDefinition = "boolean default false")
    boolean isLocked;
    @Column(columnDefinition = "datetime default now()")
    LocalDateTime createdAt;

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Builder
    public User(Role role, String email, String password, String name, String phone, boolean isAlarmAccept, int loginFailCount, boolean isLocked, LocalDateTime createdAt) {
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

