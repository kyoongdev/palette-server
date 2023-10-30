package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.musician.dto.UserSnsRequestDto;
import com.study.palette.module.user.entity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserSns {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private int type;

    @Column(length = 150)
    private String address;

    private LocalDate createAt;


    @ManyToOne
    @JoinColumn(name="UserMusicianId")
    private UserMusician userMusician;

    @ManyToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    private User user;

    public static UserSns from(UserSnsRequestDto userSnsRequestDto, UserMusician userMusician) {
        return builder()
                .type(userSnsRequestDto.getType())
                .address(userSnsRequestDto.getAddress())
                .userMusician(userMusician)
                .user(userMusician.getUser())
                .build();
    }
}
