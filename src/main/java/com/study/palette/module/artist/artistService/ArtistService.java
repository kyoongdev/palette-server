package com.study.palette.module.artist.artistService;

import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ArtistResponseDto> artistInfo(Pageable pageable) {

        /* FilterMaster 테이블 조인 (테스트용)*/
        int key = 1;

        /* artistInfo 필터 정보 조회 */
        List<FilterInfo> filterInfo = filterInfoRepository.findAllByKey_Key(key);


        /* artistInfo 조회 조건으로 FilterMaster 테이블 코드 ArtistFile 메인이미지 여부*/
        Page<ArtistInfo> artistInfo = artistRepository.findAllByFilterInfo_CodeAndArtistFileIsThumbnail(key, true, pageable);

        return null;
    }
}
