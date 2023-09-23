package com.study.palette.module.user.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class UserArtist {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    private int positionType;

    @Column(length = 100)
    private String nickName;

    @Column(length = 20)
    private String name;

    private int groupType;

    @Column(length = 3000)
    private String introduction;

    private boolean isAuthorized;

    private LocalDate cratedAt;

    @Column(length = 24)
    private String userId;


}
