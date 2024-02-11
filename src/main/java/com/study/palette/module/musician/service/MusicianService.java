package com.study.palette.module.musician.service;


import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.mixMastering.service.MixMasteringService;
import com.study.palette.module.mrBeat.service.MrBeatService;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.MusicianApplyResponseDto;
import com.study.palette.module.musician.dto.MusicianReviewResponseDto;
import com.study.palette.module.musician.dto.MusicianSellingResponseDto;
import com.study.palette.module.musician.dto.ResponsePortfolioDto;
import com.study.palette.module.musician.dto.UpdateMusicianDto;
import com.study.palette.module.musician.dto.UpdateMusicianProfileDto;
import com.study.palette.module.musician.dto.query.FindMusicianSellingQuery;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.exception.MusicianErrorCode;
import com.study.palette.module.musician.exception.MusicianException;
import com.study.palette.module.musician.repository.UsersMusicianCustomRepository;
import com.study.palette.module.musician.repository.UsersMusicianRepository;
import com.study.palette.module.recording.service.RecordingService;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MusicianService {

  private final UsersMusicianRepository usersMusicianRepository;

  private final UsersRepository usersRepository;

  private final MixMasteringService mixMasteringService;

  private final ArtistService artistService;

  private final RecordingService recordingService;

  private final MrBeatService mrBeatService;

  private final AlbumArtService albumArtService;
  private final ModelMapper modelMapper;

  private final EntityManager entityManager;

  private final UsersMusicianCustomRepository usersMusicianCustomRepository;

  @Autowired
  public MusicianService(UsersMusicianRepository usersMusicianRepository, ModelMapper modelMapper,
      UsersRepository usersRepository, MixMasteringService mixMasteringService,
      ArtistService artistService,
      RecordingService recordingService, MrBeatService mrBeatService,
      AlbumArtService albumArtService,
      UsersMusicianCustomRepository usersMusicianCustomRepository, EntityManager entityManager) {
    this.usersMusicianRepository = usersMusicianRepository;
    this.usersRepository = usersRepository;
    this.modelMapper = modelMapper;
    this.mixMasteringService = mixMasteringService;
    this.artistService = artistService;
    this.recordingService = recordingService;
    this.mrBeatService = mrBeatService;
    this.albumArtService = albumArtService;
    this.usersMusicianCustomRepository = usersMusicianCustomRepository;
    this.entityManager = entityManager;
  }

  /* 뮤지션 생성 */
  @Transactional
  public ResponseWithIdDto createMuscian(CreateMusicianDto createMusicianDto, Users users) {

    Optional<UsersMusician> isExist = usersMusicianRepository.findByUsers(users);

    if (isExist.isPresent()) {
      throw new MusicianException(MusicianErrorCode.MUSICIAN_EXIST);
    }

    UsersMusician usersMusician = createMusicianDto.toEntity(users);

    usersMusicianRepository.save(usersMusician);

    return ResponseWithIdDto.builder().id(usersMusician.getId().toString()).build();

  }

  @Transactional
  public ResponseWithIdDto updateMusician(String id, UpdateMusicianDto updateMusicianDto) {

    UsersMusician updateUsersMusician = usersMusicianRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 뮤지션 정보가 없습니다."));

    PaletteUtils.myCopyProperties(updateMusicianDto, updateUsersMusician);

    updateUsersMusician.getUsersMusicianSns().clear();
    updateUsersMusician.getUsersMusicianPosition().clear();

//    updateMusicianDto.getSnsAddress().stream().forEach(userSnsList -> {
//      updateUsersMusician.getUsersMusicianSns().add(UsersMusicianSns.from(userSnsList, updateUsersMusician));
//    });
//
//    updateMusicianDto.getMusicianPostionType().stream().forEach(userPositionList -> {
//      updateUsersMusician.getUsersMusicianPosition().add(UsersMusicianPosition.from(userPositionList,
//          updateUsersMusician));
//    });

    usersMusicianRepository.save(updateUsersMusician);

    return ResponseWithIdDto.builder().id(String.valueOf(updateUsersMusician.getId())).build();
  }

  @Transactional
  public void deleteMusician(String id) {
    usersMusicianRepository.deleteById(id);
  }

  @Transactional
  public PaginationDto<MusicianApplyResponseDto> findMusicianApplyInfo(Pageable pageable) {

    Long count = usersMusicianRepository.count();

    List<MusicianApplyResponseDto> musiciansApply = usersMusicianRepository.findAll(pageable)
        .stream().map(MusicianApplyResponseDto::new).collect(Collectors.toList());

    PaginationDto<MusicianApplyResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        musiciansApply);

    return row;

  }

  public ResponsePortfolioDto getPortfolio(Users users) {

    UsersMusician musician = usersMusicianRepository.findByUsers(users)
        .orElseThrow(() -> new MusicianException(MusicianErrorCode.MUSICIAN_NOT_EXIST));

    ResponsePortfolioDto responsePortfolioDto = ResponsePortfolioDto.toEntity(musician);

    return responsePortfolioDto;
  }

  @Transactional
  public ResponseWithIdDto updatePortfolioProfile(UpdateMusicianProfileDto data,
      Users users) {

    UsersMusician musician = usersMusicianRepository.findByUsers(users).orElseThrow();

    usersMusicianRepository.save(data.toEntity(musician));

    return ResponseWithIdDto.builder().id(String.valueOf(musician.getId())).build();

  }

  public PaginationDto<MusicianSellingResponseDto> getMusicianSellingList(Pageable pageable,
      FindMusicianSellingQuery query) {

    Long count = usersMusicianCustomRepository.countBySellingCount(
        UUID.fromString(query.getId()));

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MusicianSellingResponseDto> musicianSellingList = usersMusicianCustomRepository.getMusicianSellingList(
            query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, MusicianSellingResponseDto.class))
        .collect(Collectors.toList());

    PaginationDto<MusicianSellingResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        musicianSellingList);

    return row;
  }

  public PaginationDto<MusicianReviewResponseDto> getReviewList(Pageable pageable, PageDto query,
      Users users) {

    Long count = usersMusicianCustomRepository.countByNotApprovedCount(users.getId());

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MusicianReviewResponseDto> musicianReviewList = usersMusicianCustomRepository.getMusicianReviewList(
            query, pageable, users)
        .stream()
        .map(data -> modelMapper.map(data, MusicianReviewResponseDto.class))
        .collect(Collectors.toList());

    PaginationDto<MusicianReviewResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        musicianReviewList);

    return row;
  }


}
