package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserPosition {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int positionType;

    @ManyToOne
    @JoinColumn(name="UserMusicianId")
    private UserMusician userMusician;

    @ManyToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    private User user;

    public static UserPosition from(Integer userPositionList, UserMusician userMusician) {
        return builder()
                .positionType(userPositionList)
                .userMusician(userMusician)
                .user(userMusician.getUser())
                .build();
    }
}
