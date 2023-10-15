package com.study.palette.common.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ResponseWithIdDto {
  private String id;
}
