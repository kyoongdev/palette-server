package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.musician.MusicianSns;
import com.study.palette.module.musician.dto.MusicianSnsRequestDto;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.UUID;
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
public class UsersMusicianSns {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private MusicianSns type;

  @Column(length = 150)
  private String address;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "usersMusicianId")
  @JsonIgnore
  private UsersMusician usersMusician;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public static UsersMusicianSns from(MusicianSnsRequestDto musicianSnsRequestDto,
      UsersMusician usersMusician) {
    return builder()
        .type(MusicianSns.findMusicianSns(musicianSnsRequestDto.getType()))
        .address(musicianSnsRequestDto.getMusicianSns())
        .createdAt(LocalDateTime.now())
        .usersMusician(usersMusician)
        .users(usersMusician.getUsers())
        .build();
  }

}
