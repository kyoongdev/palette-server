package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.musician.dto.UserSnsRequestDto;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UsersSns {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  private int type;

  @Column(length = 150)
  private String address;

  private LocalDate createAt;


  @ManyToOne
  @JoinColumn(name = "usersMusicianId")
  private UsersMusician usersMusician;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public static UsersSns from(UserSnsRequestDto userSnsRequestDto, UsersMusician usersMusician) {
    return builder()
        .type(userSnsRequestDto.getType())
        .address(userSnsRequestDto.getAddress())
        .usersMusician(usersMusician)
        .users(usersMusician.getUsers())
        .build();
  }
}
