package com.study.palette.module.albumArt.controller;

import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateRequestDto;
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
  public PaginationDto<Object> getList(@ParameterObject Q query);

  /*detail*/
  @GetMapping("/{id}/detail")
  public AlbumArtDetailResponseDto getDetail(@PathVariable String id);

  /*create*/
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseEntity<AlbumArtCreateResponseDto> create(@Valid @RequestBody AlbumArtCreateRequestDto albumArtCreateRequestDto, @Parameter(hidden = true) @GetUserInfo Users users);

  /*update*/
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MUSICIAN')")
  public void updateAlbumArt(@PathVariable String id, @RequestBody AlbumArtUpdateRequestDto albumArtUpdateRequestDto, @Parameter(hidden = true) @GetUserInfo Users users);

  /*delete*/
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAlbumArt(@PathVariable String id, @Parameter(hidden = false) @GetUserInfo Users users);
}
