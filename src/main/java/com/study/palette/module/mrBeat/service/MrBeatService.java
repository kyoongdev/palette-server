package com.study.palette.module.mrBeat.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.mrBeat.dto.CreateMrBeatDto;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.dto.UpdateMrBeatDto;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatLicenseInfo;
import com.study.palette.module.mrBeat.repository.MrBeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrBeatService {

    private final MrBeatRepository mrBeatRepository;

    @Autowired
    public MrBeatService(MrBeatRepository mrBeatRepository) {
        this.mrBeatRepository = mrBeatRepository;
    }

    public PaginationDto<MrBeatResponseDto> findMrBeat(Pageable pageable) {

        Long count = mrBeatRepository.count();

        List<MrBeatResponseDto> artists = mrBeatRepository.findAll(pageable).stream().map(MrBeatResponseDto::new).collect(Collectors.toList());

        PaginationDto<MrBeatResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), artists);

        return row;
    }

    @Transactional
    public ResponseWithIdDto createMrBeat(CreateMrBeatDto createMrBeatDto) {

        MrBeatInfo mrBeatInfo = createMrBeatDto.toEntity();

        List<MrBeatLicenseInfo> mrBeatLicneses = createMrBeatDto.getMrBeatLicenseInfo().stream()
                .map(mrBeatLicense -> MrBeatLicenseInfo.from(mrBeatLicense, mrBeatInfo))
                .toList();

        mrBeatInfo.setMrBeatLicenseInfo(mrBeatLicneses);

        mrBeatRepository.save(mrBeatInfo);


        return ResponseWithIdDto.builder().id(mrBeatInfo.getId()).build();
    }

    @Transactional
    public ResponseWithIdDto updateMrBeat(String id, UpdateMrBeatDto updateMrBeatDto) {

        MrBeatInfo mrBeatInfo = mrBeatRepository.findById(id).orElseThrow();

        PaletteUtils.myCopyProperties(updateMrBeatDto, mrBeatInfo);

        mrBeatInfo.getMrBeatLicenseInfo().clear();

        updateMrBeatDto.getMrBeatLicenseInfo().stream().forEach(mrBeatLicneses -> {
            mrBeatInfo.getMrBeatLicenseInfo().add(MrBeatLicenseInfo.from(mrBeatLicneses, mrBeatInfo));
        });

        mrBeatRepository.save(mrBeatInfo);


        return ResponseWithIdDto.builder().id(mrBeatInfo.getId()).build();
    }

    @Transactional
    public void deleteMrBeat(String id) {
        mrBeatRepository.deleteById(id);
    }
}
