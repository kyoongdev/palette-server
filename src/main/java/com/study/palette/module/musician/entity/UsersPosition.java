package com.study.palette.module.musician.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.users.entity.Users;
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
public class UsersPosition {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  private int positionType;

  @ManyToOne
  @JoinColumn(name = "usersMusicianId")
  private UsersMusician usersMusician;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  public static UsersPosition from(Integer userPositionList, UsersMusician usersMusician) {
    return builder().positionType(userPositionList).usersMusician(usersMusician).users(usersMusician.getUsers()).build();
  }
}
