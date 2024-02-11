package com.study.palette.module.musician.dto;

import com.study.palette.module.musician.entity.UsersMusician;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicianApplyResponseDto {

  private String id;

  private String stageName;

  private String name;

  private LocalDateTime createdAt;

  private LocalDateTime deadlineDate;

  public MusicianApplyResponseDto(UsersMusician usersMusician) {
    this.id = usersMusician.getId().toString();
    this.stageName = usersMusician.getStageName();
    this.name = usersMusician.getUsers().getName();
    this.createdAt = usersMusician.getCreatedAt();
    this.deadlineDate = usersMusician.getCreatedAt();
  }

}
