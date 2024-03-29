package com.study.palette.config.dummydata;

import com.study.palette.common.enums.musician.ApprovalType;
import com.study.palette.common.enums.musician.MusicianAuthorizedType;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.file.AlbumArtFileCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtContact;
import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtRequest;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.repository.AlbumArtRequestRepository;
import com.study.palette.module.artist.dto.contact.CreateArtistContactDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.file.CreateArtistFileDto;
import com.study.palette.module.artist.dto.license.CreateArtistLicenseDto;
import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.mixMastering.dto.CreateMixMasteringDto;
import com.study.palette.module.mixMastering.dto.contact.CreateMixMasteringContactDto;
import com.study.palette.module.mixMastering.dto.file.CreateMixMasteringFileDto;
import com.study.palette.module.mixMastering.dto.license.CreateMixMasteringLicenseDto;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringRequest;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.mixMastering.repository.MixMasteringRequestRepository;
import com.study.palette.module.mrBeat.dto.CreateMrBeatDto;
import com.study.palette.module.mrBeat.dto.contact.CreateMrBeatContactDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatFileDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatMusicFileDto;
import com.study.palette.module.mrBeat.dto.license.CreateMrBeatLicenseInfoDto;
import com.study.palette.module.mrBeat.entity.MrBeatContact;
import com.study.palette.module.mrBeat.entity.MrBeatFile;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatLicenseInfo;
import com.study.palette.module.mrBeat.entity.MrBeatMusicFile;
import com.study.palette.module.mrBeat.repository.MrBeatRepository;
import com.study.palette.module.musician.dto.CreateMusicianAccountDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.CreateMusicianFileDto;
import com.study.palette.module.musician.dto.CreateMusicianPositionTypeDto;
import com.study.palette.module.musician.dto.MusicianSnsRequestDto;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.entity.UsersMusicianAccount;
import com.study.palette.module.musician.entity.UsersMusicianFile;
import com.study.palette.module.musician.entity.UsersMusicianPosition;
import com.study.palette.module.musician.entity.UsersMusicianSns;
import com.study.palette.module.musician.repository.UsersMusicianRepository;
import com.study.palette.module.recording.dto.file.RecordingFileCreateRequestDto;
import com.study.palette.module.recording.dto.info.RecordingCreateRequestDto;
import com.study.palette.module.recording.dto.license.RecordingLicenseInfoCreateRequestDto;
import com.study.palette.module.recording.entity.RecordingFile;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.recording.entity.RecordingLicenseInfo;
import com.study.palette.module.recording.repository.RecordingRepository;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//@Profile("local")
@Log4j2
public class InitData implements ApplicationRunner {

  private final PasswordEncoder passwordEncoder;

  private final UsersRepository usersRepository;

  private final ArtistRepository artistRepository;

  private final AlbumArtRepository albumArtRepository;
  private final AlbumArtRequestRepository albumArtRequestRepository;

  private final MixMasteringRepository mixMasteringRepository;
  private final MixMasteringRequestRepository mixMasteringRequestRepository;

  private final RecordingRepository recordingRepository;

  private final MrBeatRepository mrBeatRepository;

  private final UsersMusicianRepository usersMusicianRepository;

  /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
  @Override
  public void run(ApplicationArguments args) throws Exception {
    //dummydata 생성 분기처리
    Users initCommUser = usersRepository.findByEmail("test@test").orElse(null);


    if (initCommUser != null) {
      log.info("이미 더미데이터가 존재합니다.");

      return;
    } else {
      log.info("더미데이터 생성 시작");

      initCommUser = usersRepository.save(Users.builder().role(Role.MUSICIAN).email("test@test").password(passwordEncoder.encode("test1234")).name("홍길동").build());

      /* mrBeat */
      List<CreateMrBeatLicenseInfoDto> mrBeatLicenseInfos = new ArrayList<>();
      List<CreateMrBeatContactDto> mrBeatContacts = new ArrayList<>();
      List<MrBeatInfo> mrBeatInfoList = new ArrayList<>();

      /* artist */
      List<CreateArtistLicenseDto> artistLicenseInfo = new ArrayList<>();
      List<CreateArtistFileDto> artistFiles = new ArrayList<>();
      List<CreateArtistContactDto> artistContacts = new ArrayList<>();
      List<ArtistInfo> artistInfoList = new ArrayList<>();

      for(int i = 0; i < 20; i++){

        for(int j = 0; j < 3; j++) {
          mrBeatLicenseInfos.add(new CreateMrBeatLicenseInfoDto(j + 1, 1000));

          mrBeatContacts.add(new CreateMrBeatContactDto(j + 1, "010-1234-1234"));

          artistLicenseInfo.add(
              new CreateArtistLicenseDto(j + 1, 1000, "servedFile" + i, 3, 1, 1, true, true, true));

          artistContacts.add(new CreateArtistContactDto(j + 1, "010-1234-1234"));

          artistFiles.add(new CreateArtistFileDto("https://pallete-file.s3.ap-northeast-2.amazonaws.com/7c540940-8dcb-43e7-8a58-91c6066c860d.png", "test.png", "7c540940-8dcb-43e7-8a58-91c6066c860d.png", 1000, "png", true));
        }

        CreateArtistDto createArtistDto = new CreateArtistDto("serviceName", "serviceInfo", "editInfo", 2, artistFiles, artistLicenseInfo, artistContacts);

        ArtistInfo artistInfo = createArtistDto.toEntity(initCommUser);

        List<ArtistLicenseInfo> artistLicenseInfos = createArtistDto.getArtistLicenseInfo().stream().map(license -> ArtistLicenseInfo.from(license, artistInfo)).toList();
        List<ArtistContact> artistContactList = createArtistDto.getArtistContactDto().stream().map(contact -> ArtistContact.from(contact, artistInfo)).toList();
        List<ArtistFile> artistFileList = createArtistDto.getArtistFileDto().stream().map(file -> ArtistFile.from(file, artistInfo)).toList();

        CreateMrBeatFileDto createMrBeatFileDto = new CreateMrBeatFileDto("www.test.com", "testFileName", "uploadfilname", 1000, "jpg", true);
        CreateMrBeatMusicFileDto createMrBeatMusicFileDto = new CreateMrBeatMusicFileDto("www.test.com", "testfilenale", "uploadfilname", 1000, "mp3", 129, true, LocalDateTime.now());
        CreateMrBeatDto createMrBeatDto = new CreateMrBeatDto("serviceName", 1, 1, 1, mrBeatLicenseInfos, mrBeatContacts, createMrBeatFileDto, createMrBeatMusicFileDto);

        MrBeatInfo mrBeatInfo = createMrBeatDto.toEntity(initCommUser);

        List<MrBeatLicenseInfo> mrBeatLicneses = createMrBeatDto.getMrBeatLicenseInfo().stream().map(mrBeatLicense -> MrBeatLicenseInfo.from(mrBeatLicense, mrBeatInfo)).toList();
        List<MrBeatContact> mrBeatContactsList = createMrBeatDto.getMrBeatContact().stream().map(mrBeatContact -> MrBeatContact.from(mrBeatContact, mrBeatInfo)).toList();
        MrBeatFile mrBeatFile = createMrBeatDto.getMrBeatFile().toEntity(mrBeatInfo);
        MrBeatMusicFile mrBeatMusicFIle = createMrBeatDto.getMrBeatMusicFile().toEntity(mrBeatInfo);

        artistInfo.setArtistLicenseInfo(artistLicenseInfos);
        artistInfo.setArtistContact(artistContactList);
        artistInfo.setArtistFile(artistFileList);
        artistInfo.updateIsSelling(true);
        artistInfo.setApprovalStatus(ApprovalType.APPROVED);

        if(i > 15) {
          artistInfo.updateIsSelling(false);
          artistInfo.setApprovalStatus(ApprovalType.REJECTED);
          artistInfo.setRefusalReason("거부 사유입니다.");
        }

        mrBeatInfo.setMrBeatLicenseInfo(mrBeatLicneses);
        mrBeatInfo.setMrBeatContact(mrBeatContactsList);
        mrBeatInfo.setMrBeatFile(mrBeatFile);
        mrBeatInfo.setMrBeatMusicFile(mrBeatMusicFIle);
        mrBeatInfo.updateIsSelling(true);
        mrBeatInfo.setApprovalStatus(ApprovalType.APPROVED);

        if(i > 10) {
          mrBeatInfo.updateIsSelling(false);
          mrBeatInfo.setApprovalStatus(ApprovalType.REJECTED);
          mrBeatInfo.setRefusalReason("거부 사유입니다.");
        }

        artistInfoList.add(artistInfo);
        artistLicenseInfo.clear();
        artistFiles.clear();
        artistContacts.clear();

        mrBeatInfoList.add(mrBeatInfo);
        mrBeatLicenseInfos.clear();
        mrBeatContacts.clear();
      }

      /* mrBeat save */
      mrBeatRepository.saveAll(mrBeatInfoList);

      /* artist save*/
      artistRepository.saveAll(artistInfoList);
    }

    //albumart
    List<AlbumArtInfo> albumArtInfos = new ArrayList<>();

    List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseCreateRequestDtos = new ArrayList<>();
    List<AlbumArtFileCreateRequestDto> albumArtFileCreateRequestDtos = new ArrayList<>();
    List<AlbumArtContactCreateDto> albumArtContactCreateDtos = new ArrayList<>();
    //mixMastering
    List<MixMasteringInfo> mixMasteringInfos = new ArrayList<>();

    List<CreateMixMasteringLicenseDto> mixMasteringLicenseInfos = new ArrayList<>();
    List<CreateMixMasteringFileDto> mixMasteringFiles = new ArrayList<>();
    List<CreateMixMasteringContactDto> mixMasteringContacts = new ArrayList<>();
    //recording
    List<RecordingInfo> recordingInfos = new ArrayList<>();

    List<RecordingLicenseInfoCreateRequestDto> recordingLicenseInfos = new ArrayList<>();
    List<RecordingFileCreateRequestDto> recordingFiles = new ArrayList<>();

    //앨범아트 더미데이터 생성
    for (int i = 0; i < 50; i++) {
      //albumartinfos

      for (int j = 0; j < 3; j++) {
        albumArtLicenseCreateRequestDtos.add(//앨범아트 라이센스 생성
            new AlbumArtLicenseInfoCreateRequestDto(j + 1, 1000, "servedFile" + i, 3, null, 3, true, true, true, true));

        albumArtFileCreateRequestDtos.add(//앨범아트 파일 생성
            new AlbumArtFileCreateRequestDto("https://pallete-file.s3.ap-northeast-2.amazonaws.com/9710afc8-d935-449e-8e3c-f60733ec5eec.png", "test2.png", "9710afc8-d935-449e-8e3c-f60733ec5eec.png", 1000, "png", false));

        mixMasteringLicenseInfos.add(//mixMastering 라이센스 생성
            new CreateMixMasteringLicenseDto(j + 1, 1000, "제공파일" + i, 3, 0, 3, true, true, true, true)
        );

        mixMasteringFiles.add(//mixMastering 파일 생성
            new CreateMixMasteringFileDto("test3.jpg", "79fdbc28-1a11-440d-b2b9-9798a26a5712.jpg", 1000, "https://pallete-file.s3.ap-northeast-2.amazonaws.com/79fdbc28-1a11-440d-b2b9-9798a26a5712.jpg", "jpg", false)
        );

        recordingLicenseInfos.add(//recording 라이센스 생성
            new RecordingLicenseInfoCreateRequestDto(j + 1, 1000, j + 2)
        );

        recordingFiles.add(//recording 파일 생성
            new RecordingFileCreateRequestDto("www.test.com", "originFileName", "originFileName", 1000, "jpg", false)
        );
      }

      for (int j = 0; j < 5; j++) {
        albumArtContactCreateDtos.add(//앨범아트 연락수단 생성
            new AlbumArtContactCreateDto(j + 1, "content" + j));

        mixMasteringContacts.add(//mixMastering 연락수단 생성
            new CreateMixMasteringContactDto(j + 1, "content" + j));
      }

      //앨범아트 생성
      AlbumArtCreateRequestDto albumArtCreateRequestDto = new AlbumArtCreateRequestDto("serviceName" + i, "serviceExplain", 2, "editInfo", albumArtLicenseCreateRequestDtos, albumArtContactCreateDtos, albumArtFileCreateRequestDtos);

      AlbumArtInfo albumArtInfo = albumArtCreateRequestDto.toEntity(initCommUser);
      List<AlbumArtLicenseInfo> licenses = albumArtCreateRequestDto.getAlbumArtLicenseInfo().stream().map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo)).toList();
      List<AlbumArtContact> contacts = albumArtCreateRequestDto.getAlbumArtContact().stream().map(contact -> AlbumArtContact.from(contact, albumArtInfo)).toList();
      List<AlbumArtFile> files = albumArtCreateRequestDto.getAlbumArtFiles().stream().map(file -> AlbumArtFile.from(file, albumArtInfo)).toList();
      albumArtInfo.setAlbumArtLicenseInfo(licenses);
      albumArtInfo.setAlbumArtContact(contacts);
      albumArtInfo.setAlbumArtFiles(files);
      albumArtInfo.updateIsSelling(true);
      albumArtInfo.setApprovalStatus(ApprovalType.APPROVED);

      if(i > 40) {
        albumArtInfo.updateIsSelling(false);
        albumArtInfo.setApprovalStatus(ApprovalType.REJECTED);
        albumArtInfo.setRefusalReason("거부 사유입니다.");
      }

      albumArtInfos.add(albumArtInfo);
      albumArtLicenseCreateRequestDtos.clear();
      albumArtContactCreateDtos.clear();
      albumArtFileCreateRequestDtos.clear();

      //mixMastering 생성
      CreateMixMasteringDto createMixMasteringDto = new CreateMixMasteringDto("serviceName" + i, "serviceExplain", "editInfo", "beforeJobMusic", "afterJobMusic", 1, mixMasteringLicenseInfos, mixMasteringFiles, mixMasteringContacts);

      MixMasteringInfo mixMasteringInfo = createMixMasteringDto.toEntity(initCommUser);
      List<MixMasteringLicenseInfo> mixMasteringLicenseInfo = mixMasteringLicenseInfos.stream().map(license -> MixMasteringLicenseInfo.from(license, mixMasteringInfo)).toList();
      List<MixMasteringContact> mixMasteringContact = mixMasteringContacts.stream().map(contact -> MixMasteringContact.from(contact, mixMasteringInfo)).toList();
      List<MixMasteringFile> mixMasteringFile = mixMasteringFiles.stream().map(file -> MixMasteringFile.from(file, mixMasteringInfo)).toList();
      mixMasteringInfo.setMixMasteringLicenseInfos(mixMasteringLicenseInfo);
      mixMasteringInfo.setMixMasteringContacts(mixMasteringContact);
      mixMasteringInfo.setMixMasteringFiles(mixMasteringFile);
      mixMasteringInfo.updateIsSelling(true);
      mixMasteringInfo.setApprovalStatus(ApprovalType.APPROVED);

      if(i > 40) {
        mixMasteringInfo.updateIsSelling(false);
        mixMasteringInfo.setApprovalStatus(ApprovalType.REJECTED);
        mixMasteringInfo.setRefusalReason("거부 사유입니다.");
      }

      mixMasteringInfos.add(mixMasteringInfo);
      mixMasteringLicenseInfos.clear();
      mixMasteringContacts.clear();
      mixMasteringFiles.clear();

      //recording
      RecordingCreateRequestDto recordingCreateRequestDto = new RecordingCreateRequestDto("serviceName" + i, "studioName", 1, 1, true, "www.예약.com", "serviceExplain", recordingFiles, recordingLicenseInfos);

      RecordingInfo recordingInfo = recordingCreateRequestDto.toEntity(initCommUser);
      List<RecordingLicenseInfo> recordingLicenseInfo = recordingLicenseInfos.stream().map(license -> RecordingLicenseInfo.from(license, recordingInfo)).toList();
      List<RecordingFile> recordingFile = recordingFiles.stream().map(file -> RecordingFile.from(file, recordingInfo)).toList();
      recordingInfo.setRecordingLicenseInfo(recordingLicenseInfo);
      recordingInfo.setRecordingFile(recordingFile);
      recordingInfo.updateIsSelling(true);
      recordingInfo.setApprovalStatus(ApprovalType.APPROVED);

      if(i > 40) {
        recordingInfo.updateIsSelling(false);
        recordingInfo.setApprovalStatus(ApprovalType.REJECTED);
        recordingInfo.setRefusalReason("거부 사유입니다.");
      }

      recordingInfos.add(recordingInfo);
      recordingLicenseInfos.clear();
      recordingFiles.clear();
    }
    albumArtRepository.saveAll(albumArtInfos);
    mixMasteringRepository.saveAll(mixMasteringInfos);
    recordingRepository.saveAll(recordingInfos);

    //구매의뢰
    for (int i = 0; i < 20; i++) {// 앨범아트 구매의뢰 더미데이터 생성
      Random random = new Random();
      int randomValue = random.nextInt(100000 - 2000) + 2000;
      int randomValue2 = random.nextInt(20 - 1) + 1;

      UUID serviceId = albumArtRepository.findByServiceName("serviceName" + randomValue2).getId();
      UUID serviceId2 = mixMasteringRepository.findByServiceName("serviceName" + randomValue2).getId();

      AlbumArtRequest albumArtRequest = AlbumArtRequest.builder().albumArtInfo(albumArtRepository.findById(serviceId).get()).users(initCommUser).createdAt(LocalDateTime.now()).build();
      MixMasteringRequest mixMasteringRequest = MixMasteringRequest.builder().mixMasteringInfo(mixMasteringRepository.findById(serviceId2).get()).users(initCommUser).createdAt(LocalDateTime.now()).build();

      albumArtRequestRepository.save(albumArtRequest);
      mixMasteringRequestRepository.save(mixMasteringRequest);
    }

    /* musician */
    List<MusicianSnsRequestDto> musicianSnsRequestDtos = new ArrayList<>();
    List<CreateMusicianPositionTypeDto> musicianPositionTypeDtos = new ArrayList<>();

    List<UsersMusician> usersMusicianList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {

      if(i > 0) {
        initCommUser = usersRepository.save(Users.builder().role(Role.MUSICIAN).email("test@test" + i).password(passwordEncoder.encode("test1234")).name("홍길동" + i).build());
      }

      for (int j = 0; j < 3; j++) {
        musicianSnsRequestDtos.add(new MusicianSnsRequestDto(j + 1, "www.test.com"));
      }

      for (int j = 0; j < 3; j++) {
        musicianPositionTypeDtos.add(new CreateMusicianPositionTypeDto(1));
      }

      CreateMusicianFileDto createMusicianFileDto = new CreateMusicianFileDto("www.test.com", "testfilenale", "uploadfilname", "uploadFilePath", 1000, "jpg", true);
      CreateMusicianAccountDto createMusicianAccountDto = new CreateMusicianAccountDto(1, "123412341234", "홍길동" + i);
      CreateMusicianDto createMusicianDto = new CreateMusicianDto("stageName" + i, "name" + i, 1, musicianSnsRequestDtos, musicianPositionTypeDtos, createMusicianFileDto, createMusicianAccountDto);



      UsersMusician usersMusician = createMusicianDto.toEntity(initCommUser);

      UsersMusicianFile usersMusicianFile = createMusicianDto.getCreateMusicianFileDto().toEntity(usersMusician);
      UsersMusicianAccount usersMusicianAccount = createMusicianDto.getCreateMusicianAccountDto().toEntity(usersMusician);

      List<UsersMusicianSns> snsList = createMusicianDto.getSnsAddress().stream().map(sns -> UsersMusicianSns.from(sns, usersMusician)).toList();
      List<UsersMusicianPosition> positionList = createMusicianDto.getPositionType().stream().map(position -> UsersMusicianPosition.from(position, usersMusician)).toList();


      usersMusician.setUsersMusicianSns(snsList);
      usersMusician.setUsersMusicianPosition(positionList);
      usersMusician.setUsersMusicianFile(usersMusicianFile);
      usersMusician.setUsersMusicianAccount(usersMusicianAccount);
      usersMusician.setIsAuthorized(MusicianAuthorizedType.APPROVED);
      usersMusicianList.add(usersMusician);

      musicianSnsRequestDtos.clear();
      musicianPositionTypeDtos.clear();

    }

    /* musician save */
    usersMusicianRepository.saveAll(usersMusicianList);
  }
}