package com.study.palette.module.albumArt.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateReqeustDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.exception.AlbumArtErrorCode;
import com.study.palette.module.albumArt.exception.AlbumArtException;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumArtService {

    private final AlbumArtRepository albumArtRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumArtService(AlbumArtRepository albumArtRepository, ModelMapper modelMapper) {
        this.albumArtRepository = albumArtRepository;
        this.modelMapper = modelMapper;
    }

    /* AlbumArt 필터 포함 조회*/
    @Transactional(readOnly = true)
    public PaginationDto<AlbumArtResponseDto> getAlbumArts(FindAlbumArtQuery query, Pageable pageable) {
        Long count = albumArtRepository.count();

        if (count == 0) {
            return PaginationDto.of(new PagingDto(pageable, count), List.of());
        }

        List<AlbumArtResponseDto> artists = albumArtRepository.findAll(query, pageable)
                .stream()
                .map(data -> modelMapper.map(data, AlbumArtResponseDto.class))
                .collect(Collectors.toList());

        PaginationDto<AlbumArtResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), artists);

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
    public AlbumArtCreateResponseDto createAlbumArt(AlbumArtCreateRequestDto albumArtCreateRequestDto, User user) {
        return new AlbumArtCreateResponseDto(albumArtRepository.save(albumArtCreateRequestDto.toEntity(user)));
    }

    /* AlbumArt 수정*/
    @Transactional
    public void updateAlbumArt(String id, AlbumArtUpdateReqeustDto albumArtUpdateReqeustDto, User user) {
        AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));

        //본인이 작성한 글인지 체크
        if (!albumArtInfo.getUser().getId().equals(user.getId())) {
            throw new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_YOURS);
        }

        PaletteUtils.myCopyProperties(albumArtUpdateReqeustDto, albumArtInfo);

        // update 전 초기화
        albumArtInfo.getAlbumArtLicenseInfo().clear();
//        albumArtInfo.getAlbumArtFile().clear(); TODO 파일 구현 후 추가

        albumArtUpdateReqeustDto.getAlbumArtLicenseInfo().stream().forEach(license -> {
            albumArtInfo.getAlbumArtLicenseInfo().add(AlbumArtLicenseInfo.from(license, albumArtInfo));
        });

        //TODO 파일 구현 후 추가
//        albumArtUpdateReqeustDto.getAlbumArtLicenseInfo().stream().forEach(license -> {
//            albumArtInfo.getAlbumArtLicenseInfo().add(AlbumArtLicenseInfo.from(license, albumArtInfo));
//        });

        albumArtRepository.save(albumArtInfo);
    }

    /* AlbumArt 삭제*/
    @Transactional
    public void deleteAlbumArt(String id) {
        AlbumArtInfo albumArtInfo = albumArtRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AlbumArtException(AlbumArtErrorCode.ALBUM_ART_NOT_FOUND));
        albumArtRepository.delete(albumArtInfo);
    }
}
