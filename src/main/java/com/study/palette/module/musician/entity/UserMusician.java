package com.study.palette.module.musician.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserMusician {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @OneToMany(mappedBy = "userMusician", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserPosition> userPosition = new ArrayList<>();

    @OneToMany(mappedBy = "userMusician", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<UserSns> userSns = new ArrayList<>();

    @Column(length = 100)
    private String nickName;

    @Column(length = 20)
    private String name;

    private int groupType;

    @Column(length = 3000)
    private String introduction;

    private boolean isAuthorized;

    private LocalDate cratedAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userId")
    private User user;

    public void setUserPosition(List<UserPosition> userPosition) {
        this.userPosition = userPosition;
    }

    public void setUserSns(List<UserSns> userSns) {
        this.userSns = userSns;
    }

}
