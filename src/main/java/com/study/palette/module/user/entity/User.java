package com.study.palette.module.user.entity;

import jdk.jfr.Timestamp;
import com.study.palette.module.user.entity.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter     // TODO dto <--> entity 전환을 copyProperty 로 하기위해 추가함--> 추후 좀더 다른 방법 알아보면 될듯
@NoArgsConstructor
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
    @CreatedDate
    LocalDateTime createdAt;

    @Column(columnDefinition = "datetime")
    LocalDateTime deletedAt;

    public void updateLoginFailCount(int loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public void updateIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void generateDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }

    public void resetDeletedAt() {
        this.deletedAt = null;
    }

    @Builder
    public User(Role role, String email, String password, String name, String phone, boolean isAlarmAccept, int loginFailCount, boolean isLocked, LocalDateTime createdAt) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.isAlarmAccept = isAlarmAccept;
    }

    @OneToOne
    @JoinColumn(name = "userId")
    private UserArtist userArtist;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserFile> userFile = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserFollowing> userFolloing = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserFollower> userFollower = new ArrayList<>();

}
