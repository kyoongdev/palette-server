package com.study.palette.module.mixMastering.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MixMasteringErrorCode implements ErrorCodeInterface {

  MIX_MASTERING_NOT_FOUND(404, "믹스 마스터링 정보를 찾을 수 없습니다."),
  CONFLICT_ERROR(409, "Conflict"),
  MIX_MASTERING_NOT_YOURS(403, "본인이 작성하지 않은 게시글입니다."),
  MIX_MASTERING_REQUEST_ALREADY_EXISTS(409, "이미 구매의뢰를 하셨습니다.");

  private final int status;
  private final String message;
}
