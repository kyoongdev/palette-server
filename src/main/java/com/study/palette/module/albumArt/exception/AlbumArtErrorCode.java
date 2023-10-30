package com.study.palette.module.albumArt.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumArtErrorCode implements ErrorCodeInterface {

    ALBUM_ART_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    ALBUM_ART_NOT_YOURS(403, "본인이 작성하지 않은 게시글입니다."),
    ALBUM_ART_NOT_SORT(404, "정렬 데이터가 없습니다."),
    ALBUM_ART_REQUEST_ALREADY_EXISTS(409, "오늘 이미 의뢰한 게시글입니다.");


//    INVALID_ALBUM_ART_DATA(400, "올바르지 않은 게시글 데이터입니다."),
//    ALBUM_ART_ALREADY_EXISTS(409, "이미 존재하는 게시글입니다.");


    private final int status;
    private final String message;
}
