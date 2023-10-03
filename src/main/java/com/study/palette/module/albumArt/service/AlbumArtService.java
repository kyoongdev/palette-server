package com.study.palette.module.albumArt.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.albumArt.dto.AlbumArtCreateDto;
import com.study.palette.module.albumArt.dto.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.AlbumArtResponseDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumArtService {

    private AlbumArtRepository albumArtRepository;
    private FilterInfoRepository filterInfoRepository;

    @Autowired
    public AlbumArtService(AlbumArtRepository albumArtRepository, FilterInfoRepository filterInfoRepository) {
        this.filterInfoRepository = filterInfoRepository;
        this.albumArtRepository = albumArtRepository;
    }

    /* AlbumArt 필터 포함 조회*/
    @Transactional(readOnly = true)
    public PaginationDto<AlbumArtResponseDto> getAlbumArts(Pageable pageable) {
        Long count  = albumArtRepository.count();
        List<AlbumArtResponseDto> artists = albumArtRepository.findAll( pageable).stream().map(AlbumArtResponseDto::new).collect(Collectors.toList());
        PaginationDto<AlbumArtResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),artists);
        return row;
    }

    /* AlbumArt 상세 조회*/
    @Transactional(readOnly = true)
    public AlbumArtResponseDto getAlbumArtWithDto(String id) {
        return new AlbumArtResponseDto(albumArtRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범아트가 없습니다. id=" + id)));
    }

    /* AlbumArt 등록*/
    @Transactional
    public AlbumArtDetailResponseDto createAlbumArt(AlbumArtCreateDto albumArtCreateDto) {
        //TODO 업로드할 이미지 받아서 이름 변환 후 리스트로 저장하는 작업 해야함 and 소개에 들어갈 파일들 까지

        return new AlbumArtDetailResponseDto(albumArtRepository.save(new AlbumArtInfo(

        )));
    }

    /* AlbumArt 수정*/

    /* AlbumArt 삭제*/


}
