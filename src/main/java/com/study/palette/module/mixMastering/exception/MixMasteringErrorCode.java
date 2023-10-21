package com.study.palette.module.mixMastering.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MixMasteringErrorCode {
  CONFLICT_ERROR(409, "Conflict");


  private final int status;
  private final String message;
}
