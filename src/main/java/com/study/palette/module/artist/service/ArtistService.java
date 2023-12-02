package com.study.palette.module.artist.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistDetailResponseDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.UpdateArtistDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistRequest;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.artist.repository.ArtistRequestRepository;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

  private final ArtistRepository artistRepository;

  private final ArtistRequestRepository artistRequestRepository;

  private final ModelMapper modelMapper;


  @Autowired
  public ArtistService(ArtistRepository artistRepository,
      ArtistRequestRepository artistRequestRepository, ModelMapper modelMapper) {
    this.artistRepository = artistRepository;
    this.artistRequestRepository = artistRequestRepository;
    this.modelMapper = modelMapper;

  }

  /* artistInfo artistInfo 필터 정보 조회*/
  public PaginationDto<ArtistResponseDto> findArtists(FindArtistsQuery query, Pageable pageable) {
    //페이지네이션을 이용해서 정보를 조회하기 위해서는
    //크게
    // count: 해당 요청에 맞는 "전체 데이터 개수"
    // page : 현재 요청의 "페이지 번호"
    // limit : 한 페이지에 보여줄 "데이터 개수"
    //가 필요합니다.
    Long count = artistRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<ArtistResponseDto> artists = artistRepository.findAll(query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, ArtistResponseDto.class))
        .collect(Collectors.toList());

    PaginationDto<ArtistResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        artists);

    return row;
  }

  public ArtistDetailResponseDto findArtistsDetail(String id) {

    ArtistInfo artists = artistRepository.findById(id).orElse(null);

    ArtistDetailResponseDto artistDetail = ArtistDetailResponseDto.toEntity(artists);

    return artistDetail;
  }


  @Transactional
  public ResponseWithIdDto createArtist(CreateArtistDto createArtistDto, Users users) {

    ArtistInfo aritstInfo = createArtistDto.toEntity(users);

    List<ArtistLicenseInfo> artistLicenses = createArtistDto.getArtistLicenseInfo().stream()
        .map(artistLicense -> ArtistLicenseInfo.from(artistLicense, aritstInfo))
        .toList();

    List<ArtistContact> artistContacts = createArtistDto.getArtistContactDto().stream()
        .map(artistContact -> ArtistContact.from(artistContact, aritstInfo))
        .toList();

    List<ArtistFile> artistFiles = createArtistDto.getArtistFileDto().stream()
        .map(artistFile -> ArtistFile.from(artistFile, aritstInfo))
        .toList();

    aritstInfo.setArtistFile(artistFiles);
    aritstInfo.setArtistLicenseInfo(artistLicenses);
    aritstInfo.setArtistContact(artistContacts);

    artistRepository.save(aritstInfo);

    return ResponseWithIdDto.builder().id(aritstInfo.getId().toString()).build();

  }

  public ResponseWithIdDto updateArtist(String id, UpdateArtistDto data, Users users) {

    ArtistInfo artistInfo = artistRepository.findById(id).orElseThrow();

    PaletteUtils.myCopyProperties(data, artistInfo);

    artistInfo.getArtistContact().clear();
    artistInfo.getArtistLicenseInfo().clear();
    artistInfo.getArtistFile().clear();

    data.getArtistFileDto().stream().forEach(artistFiles -> {
      artistInfo.getArtistFile().add(ArtistFile.from(artistFiles, artistInfo));
    });

    data.getArtistLicenseInfo().stream().forEach(artistLicenses -> {
      artistInfo.getArtistLicenseInfo().add(ArtistLicenseInfo.from(artistLicenses, artistInfo));
    });

    data.getArtistContactDto().stream().forEach(artistContacts -> {
      artistInfo.getArtistContact().add(ArtistContact.from(artistContacts, artistInfo));
    });

    artistRepository.save(artistInfo);

    return ResponseWithIdDto.builder().id(artistInfo.getId()).build();
  }


  public Boolean artistDelete(String id, Users users) {

    ArtistInfo artistInfo = artistRepository.findById(id).orElseThrow();

    artistRepository.deleteById(id);

    return true;
  }


  public void createArtistRequest(String id, Users users) throws Exception {
    ArtistInfo artistInfo = artistRepository.findById(id).orElseThrow();

    //오늘 이미 구매의뢰를 했는지 체크
    Optional<ArtistRequest> artistRequest = artistRequestRepository.findByArtistInfoAndUserAndCreatedAt(
        users, artistInfo, LocalDate.now());

    if (artistRequest.isPresent()) {
      throw new Exception("이미 구매의뢰를 하셨습니다.");
    }

    artistRequestRepository.save(ArtistRequest.builder()
        .artistInfo(artistInfo)
        .users(users)
        .createAt(LocalDate.now())
        .build()
    );
  }
}
