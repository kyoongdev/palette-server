package com.study.palette.module.albumArt.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "앨범아트", description = "앨범아트")
@RequestMapping("/albumArts")
public class AlbumArtControllerTest implements CommonRestController{

  private AlbumArtService albumArtService;

  @Autowired
  public AlbumArtControllerTest(AlbumArtService albumArtService) {
    this.albumArtService = albumArtService;
  }

  @Override
  public PaginationDto<AlbumArtsResponseDto> getList(FindAlbumArtQuery query) {
    return null;
  }

  @Override
  public AlbumArtDetailResponseDto getDetail(String id) {
    return null;
  }

  @Override
  public ResponseEntity<AlbumArtCreateResponseDto> create(AlbumArtCreateRequestDto albumArtCreateRequestDto, Users users) {
    return null;
  }

  @Override
  public void updateAlbumArt(String id, AlbumArtUpdateRequestDto albumArtUpdateRequestDto, Users users) {

  }

  @Override
  public void deleteAlbumArt(String id, Users users) {

  }
}
