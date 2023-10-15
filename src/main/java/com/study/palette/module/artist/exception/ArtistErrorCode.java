package com.study.palette.module.artist.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArtistErrorCode {
  CONFLICT_ERROR(409, "Conflict");


  private final int status;
  private final String message;
}
