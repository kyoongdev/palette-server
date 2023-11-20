package com.study.palette.module.albumArt.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class CommonResponseDto<D> {
  private final D data;
  private final String message;
  private final String code;
}
