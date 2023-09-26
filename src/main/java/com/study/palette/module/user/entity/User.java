package com.study.palette.module.user.entity;

import com.study.palette.module.user.entity.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter     // TODO dto <--> entity 전환을 copyProperty 로 하기위해 추가함--> 추후 좀더 다른 방법 알아보면 될듯
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(columnDefinition = "boolean default false")
    boolean isAlarmAccept;
    public String getRoleKey() {
        return this.role.getKey();
    }

    @Column(columnDefinition = "int default 0")
    int loginFailCount;
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

    private boolean isAlarmAccept;

    private int loginFailCount;

    private boolean isLocked;

    private LocalDateTime createdAt;

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
