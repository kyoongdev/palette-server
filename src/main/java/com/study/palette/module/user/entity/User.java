package com.study.palette.module.user.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 20)
    private String auth;

    @Column(length = 20)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String phone;

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
