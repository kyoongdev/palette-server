package com.study.palette.module.albumArt.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.albumArt.dto.AlbumArtResponseDto;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public PaginationDto<AlbumArtResponseDto> findAlbumArtInfos(Pageable pageable) {
        Long count  = albumArtRepository.count();
        List<AlbumArtResponseDto> artists = albumArtRepository.findAll( pageable).stream().map(AlbumArtResponseDto::new).collect(Collectors.toList());
        PaginationDto<AlbumArtResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),artists);
        return row;
    }
}
