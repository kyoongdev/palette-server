package com.study.palette.module.albumArt.controller;

import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Parameter;
import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public interface CommonRestController<Q extends PageDto> {

  /*list*/
  @GetMapping("")
  public PaginationDto<CommonResponseDto> getList(@ParameterObject Q query);

  /*detail*/
  @GetMapping("/{id}/detail")
  public CommonResponseDto getDetail(@PathVariable String id);

  /*create*/
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseEntity<CommonResponseDto> create(@Valid @RequestBody CommonRequestDto createRequestDto, @Parameter(hidden = true) @GetUserInfo Users users);

  /*update*/
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MUSICIAN')")
  public void updateAlbumArt(@PathVariable String id, @RequestBody CommonRequestDto updateRequestDto, @Parameter(hidden = true) @GetUserInfo Users users);

  /*delete*/
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAlbumArt(@PathVariable String id, @Parameter(hidden = false) @GetUserInfo Users users);
}
