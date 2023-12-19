package com.study.palette.common.controller;

import com.study.palette.common.dto.CommonResponseDto;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Parameter;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public interface CommonRestController<T extends PageDto> {

  //목록 조회
  @GetMapping("")
  public PaginationDto<CommonResponseDto> getList(@ParameterObject T query);

  //상세조회
  @GetMapping("/{id}/detail")
  public ResponseEntity<Object> getDetail(@PathVariable String id);

  //등록
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Object> create(
      @Valid @RequestBody Object createRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users);

  //수정
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable String id,
      @RequestBody Object updateRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users);

  //삭제
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id,
      @Parameter(hidden = false) @GetUserInfo Users users);
}
