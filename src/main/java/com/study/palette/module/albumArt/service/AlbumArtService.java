package com.study.palette.module.albumArt.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.albumArt.dto.AlbumArtCreateDto;
import com.study.palette.module.albumArt.dto.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.AlbumArtResponseDto;
import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumArtService {

    private final AlbumArtRepository albumArtRepository;

    @Autowired
    public AlbumArtService(AlbumArtRepository albumArtRepository) {
        this.albumArtRepository = albumArtRepository;
    }

    /* AlbumArt 필터 포함 조회*/
    @Transactional(readOnly = true)
    public PaginationDto<AlbumArtResponseDto> getAlbumArts(Pageable pageable) {
        Long count = albumArtRepository.count();
        List<AlbumArtResponseDto> artists = albumArtRepository.findAll(pageable).stream().map(AlbumArtResponseDto::new).collect(Collectors.toList());
        PaginationDto<AlbumArtResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), artists);
        return row;
    }

    /* AlbumArt 상세 조회*/
    @Transactional(readOnly = true)
    public AlbumArtResponseDto getAlbumArtWithDto(String id) {
        return new AlbumArtResponseDto(albumArtRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범아트가 없습니다. id=" + id)));
    }

    /* AlbumArt 등록*/
    @Transactional
    public AlbumArtDetailResponseDto createAlbumArt(AlbumArtCreateDto albumArtCreateDto, User user) {
        AlbumArtInfo albumArtInfo = AlbumArtInfo.builder()
                .serviceName(albumArtCreateDto.getServiceName())
                .serviceExplain(albumArtCreateDto.getServiceExplain())
                .serviceStatus(albumArtCreateDto.isServiceStatus())
                .salesType(albumArtCreateDto.getSalesType())
                .editInfo(albumArtCreateDto.getEditInfo())
                .user(user)
                .albumArtLicenseInfo(new ArrayList<>())
//                .albumArtFile(new ArrayList<>())
                .build();



        //TODO 업로드할 이미지 받아서 이름 변환 후 리스트로 저장하는 작업 해야함 and 소개에 들어갈 파일들 까지
//        List<AlbumArtFile> files = albumArtCreateDto.getAlbumArtFiles().stream()
//                .map(file -> AlbumArtFile.from(file, albumArtInfo))
//                .toList();

        List<AlbumArtLicenseInfo> licenseInfos = albumArtCreateDto.getAlbumArtLicenseInfo().stream()
                .map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo))
                .toList();

        return new AlbumArtDetailResponseDto(albumArtRepository.save(albumArtInfo));
    }

    /* AlbumArt 수정*/

    /* AlbumArt 삭제*/


}
