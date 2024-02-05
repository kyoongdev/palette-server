package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.enums.musician.MusicianPostionType;
import com.study.palette.module.musician.dto.CreateMusicianPositionTypeDto;
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
public class UsersMusicianPosition {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private MusicianPostionType postionType;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "usersMusicianId")
  @JsonIgnore
  private UsersMusician usersMusician;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public static UsersMusicianPosition from(CreateMusicianPositionTypeDto musicianPositionTypeDto,
      UsersMusician usersMusician) {
    return builder().postionType(MusicianPostionType.findPositionType(musicianPositionTypeDto.getType()))
        .createdAt(LocalDateTime.now())
        .usersMusician(usersMusician)
        .users(usersMusician.getUsers()).build();
  }

}
