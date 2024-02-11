package com.study.palette.module.musician.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSnsRequestDto {

  private int type;

  private String address;

  private LocalDate createAt;


}
