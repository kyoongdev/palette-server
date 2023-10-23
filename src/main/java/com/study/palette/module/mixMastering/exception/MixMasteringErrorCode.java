package com.study.palette.module.mixMastering.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MixMasteringErrorCode implements ErrorCodeInterface {

  MIX_MASTERING_NOT_FOUND(404, "믹스 마스터링 정보를 찾을 수 없습니다."),
  CONFLICT_ERROR(409, "Conflict");


  private final int status;
  private final String message;
}
