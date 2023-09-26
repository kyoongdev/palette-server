package com.study.palette.module.artist.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private FilterInfoRepository filterInfoRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, FilterInfoRepository filterInfoRepository) {
        this.filterInfoRepository = filterInfoRepository;
        this.artistRepository = artistRepository;
    }

    /* artistInfo artistInfo 필터 정보 조회*/
    public PaginationDto<ArtistResponseDto> findArtistInfos(Pageable pageable) {
        //페이지네이션을 이용해서 정보를 조회하기 위해서는
        //크게
        // count: 해당 요청에 맞는 "전체 데이터 개수"
        // page : 현재 요청의 "페이지 번호"
        // limit : 한 페이지에 보여줄 "데이터 개수"
        //가 필요합니다.
        Long count  = artistRepository.count();
        List<ArtistResponseDto> artists = artistRepository.findAll( pageable).stream().map(ArtistResponseDto::new).collect(Collectors.toList());


        PaginationDto<ArtistResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),artists);
        return row;
    }
}
