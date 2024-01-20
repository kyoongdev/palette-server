package com.study.palette.module.users.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailExistsDto {

  @Schema(name="존재 여부")
  private boolean isExists;

}
