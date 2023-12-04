package com.study.palette.module.albumArt.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtRequest;
import com.study.palette.module.albumArt.exception.AlbumArtErrorCode;
import com.study.palette.module.albumArt.exception.AlbumArtException;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.repository.AlbumArtRequestRepository;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumArtService {

  private final AlbumArtRepository albumArtRepository;
  private final AlbumArtRequestRepository albumArtRequestRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public AlbumArtService(AlbumArtRepository albumArtRepository,
      AlbumArtRequestRepository albumArtRequestRepository, ModelMapper modelMapper) {
    this.albumArtRepository = albumArtRepository;
    this.albumArtRequestRepository = albumArtRequestRepository;
    this.modelMapper = modelMapper;
  }

  /* AlbumArt 필터 포함 조회*/
  @Transactional(readOnly = true)
  public PaginationDto<AlbumArtsResponseDto> getAlbumArts(FindAlbumArtQuery query,
      Pageable pageable) {
    Long count = albumArtRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<AlbumArtsResponseDto> artists = albumArtRepository.findAll(query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, AlbumArtsResponseDto.class))
        .collect(Collectors.toList());

    if(artists.size() == 0) {
      throw new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND);
    }

    PaginationDto<AlbumArtsResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        artists);

    return row;
  }

  /* AlbumArt 상세 조회*/
  @Transactional(readOnly = true)
  public AlbumArtDetailResponseDto getAlbumArtWithDto(String id) {
    AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));
    return new AlbumArtDetailResponseDto(albumArtInfo);
  }

  /* AlbumArt 등록*/
  @Transactional
  public AlbumArtCreateResponseDto createAlbumArt(AlbumArtCreateRequestDto albumArtCreateRequestDto,
      Users users) {
    return new AlbumArtCreateResponseDto(
        albumArtRepository.save(albumArtCreateRequestDto.toEntity(users)));
  }

  /* AlbumArt 수정*/
  @Transactional
  public void updateAlbumArt(String id, AlbumArtUpdateRequestDto albumArtUpdateRequestDto,
      Users users) {
    AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!albumArtInfo.getUsers().getId().equals(users.getId())) {
      throw new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_YOURS);
    }

    albumArtRepository.save(albumArtUpdateRequestDto.toEntity(albumArtInfo));
  }

  /*
  AlbumArt 구매의뢰 하루에 한번만 가능
  */
  @Transactional
  public void createAlbumArtRequest(String id, Users users) {
    AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));

    //오늘 이미 구매의뢰를 했는지 체크
    Optional<AlbumArtRequest> albumArtRequest = albumArtRequestRepository.findByAlbumArtInfoAndUsersAndCreatedAt(
        users, albumArtInfo, LocalDateTime.now());

    if (albumArtRequest.isPresent()) {
      throw new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_REQUEST_ALREADY_EXISTS);
    }

    albumArtRequestRepository.save(AlbumArtRequest.builder()
        .albumArtInfo(albumArtInfo)
        .users(users)
        .createAt(LocalDateTime.now())
        .build()
    );
  }

  /* AlbumArt 삭제*/
  @Transactional
  public void deleteAlbumArt(String id, Users users) {
    AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!albumArtInfo.getUsers().getId().equals(users.getId())) {
      throw new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_YOURS);
    }

    albumArtRepository.delete(albumArtInfo);
  }
}
