package com.study.palette.config.dummydata;

import com.study.palette.common.enums.Contact;
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
import com.study.palette.module.artist.dto.CreateArtistContactDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistLicenseDto;
import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.recording.repository.RecordingRepository;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.time.LocalDate;
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

  private final ArtistRepository artistRepository;
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final AlbumArtRepository albumArtRepository;
  private final AlbumArtRequestRepository albumArtRequestRepository;
  private final MixMasteringRepository mixMasteringRepository;
  private final RecordingRepository recordingRepository;


  /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
  @Override
  public void run(ApplicationArguments args) throws Exception {
    //dummydata 생성 분기처리
    boolean isExists = usersRepository.findByEmail("test@test").isPresent();

    if (isExists) {
      log.info("이미 더미데이터가 존재합니다.");
      return;
    }

    Users initCommUser = usersRepository.save(Users.builder().role(Role.MUSICIAN).email("test@test").password(passwordEncoder.encode("test1234")).name("홍길동").build());

    /* artist */
    List<CreateArtistLicenseDto> artistLicenseInfo = new ArrayList<>();
    List<CreateArtistFileDto> artistFiles = new ArrayList<>();
    List<CreateArtistContactDto> artistContacts = new ArrayList<>();
    List<ArtistInfo> artistInfoList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {

      for (int j = 0; j < 3; j++) {
        artistLicenseInfo.add(new CreateArtistLicenseDto(10, 1000, "servedFile" + i, 3, 1, 1, true, true, true));
      }

      for (int j = 0; j < 3; j++) {
        artistContacts.add(new CreateArtistContactDto(Contact.findContact(j + 1), "010-1234-1234"));
      }

      for (int j = 0; j < 3; j++) {
        artistFiles.add(new CreateArtistFileDto("www.test.com", "testfilenale", "uploadfilname", 1000, "jpg", true));
      }

      CreateArtistDto createArtistDto = new CreateArtistDto("serviceName", "serviceInfo", "editInfo", 2, true, LocalDate.now(), artistFiles, artistLicenseInfo, artistContacts);

      ArtistInfo artistInfo = createArtistDto.toEntity(initCommUser);

      List<ArtistLicenseInfo> artistLicenseInfos = createArtistDto.getArtistLicenseInfo().stream().map(license -> ArtistLicenseInfo.from(license, artistInfo)).toList();

      List<ArtistContact> artistContactList = createArtistDto.getArtistContactDto().stream().map(contact -> ArtistContact.from(contact, artistInfo)).toList();

      List<ArtistFile> artistFileList = createArtistDto.getArtistFileDto().stream().map(file -> ArtistFile.from(file, artistInfo)).toList();

      artistInfo.setArtistLicenseInfo(artistLicenseInfos);
      artistInfo.setArtistContact(artistContactList);
      artistInfo.setArtistFile(artistFileList);
      artistInfoList.add(artistInfo);

      artistLicenseInfo.clear();
      artistFiles.clear();
      artistContacts.clear();

    }

    /* artist save*/
    artistRepository.saveAll(artistInfoList);

    //albumart
    List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseCreateRequestDtos = new ArrayList<>();
    List<AlbumArtContactCreateDto> AlbumArtContactCreateDtos = new ArrayList<>();
    List<AlbumArtFileCreateRequestDto> albumArtFileCreateRequestDtos = new ArrayList<>();
    List<AlbumArtInfo> albumArtInfos = new ArrayList<>();
    //mixMastering
    List<MixMasteringInfo> mixMasteringInfos = new ArrayList<>();

    //앨범아트 더미데이터 생성
    for (int i = 0; i < 50; i++) {
      //albumart

      albumArtFileCreateRequestDtos.add(//앨범아트 파일 생성
          new AlbumArtFileCreateRequestDto("www.test.com", "testfilenale", "uploadfilname", 1000, "jpg", true));
      for (int j = 0; j < 3; j++) {
        albumArtLicenseCreateRequestDtos.add(//앨범아트 라이센스 생성
            new AlbumArtLicenseInfoCreateRequestDto(1, 1000, "servedFile" + i, 3, null, 3, true, true, true, true));

        albumArtFileCreateRequestDtos.add(//앨범아트 파일 생성
            new AlbumArtFileCreateRequestDto("www.test.com", "testfilenale", "uploadfilname", 1000, "jpg", false));
      }

      for (int j = 0; j < 5; j++) {
        AlbumArtContactCreateDtos.add(//앨범아트 연락수단 생성
            new AlbumArtContactCreateDto(j + 1, "content" + j));
      }

      AlbumArtCreateRequestDto albumArtCreateRequestDto = new AlbumArtCreateRequestDto("serviceName" + i, "serviceExplain", 2, "editInfo", albumArtLicenseCreateRequestDtos, AlbumArtContactCreateDtos, albumArtFileCreateRequestDtos, true);

      AlbumArtInfo albumArtInfo = albumArtCreateRequestDto.toEntity(initCommUser);
      List<AlbumArtLicenseInfo> licenses = albumArtCreateRequestDto.getAlbumArtLicenseInfo().stream().map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo)).toList();
      List<AlbumArtContact> contacts = albumArtCreateRequestDto.getAlbumArtContact().stream().map(contact -> AlbumArtContact.from(contact, albumArtInfo)).toList();
      List<AlbumArtFile> files = albumArtCreateRequestDto.getAlbumArtFiles().stream().map(file -> AlbumArtFile.from(file, albumArtInfo)).toList();
      albumArtInfo.setAlbumArtLicenseInfo(licenses);
      albumArtInfo.setAlbumArtContact(contacts);
      albumArtInfo.setAlbumArtFiles(files);
      albumArtInfos.add(albumArtInfo);
      albumArtLicenseCreateRequestDtos.clear();
      AlbumArtContactCreateDtos.clear();
      albumArtFileCreateRequestDtos.clear();

      //mixMastering
      List<MixMasteringContact> mixMasteringContacts = new ArrayList<>();
      List<MixMasteringLicenseInfo> mixMasteringLicenseInfos = new ArrayList<>();

      MixMasteringInfo mixMasteringInfo = MixMasteringInfo.builder().serviceName("믹스 마스터링" + Integer.toString(i)).beforeJobMusic("").afterJobMusic("").serviceExplain("").editInfo("").serviceStatus(true).genre((i % 5) + 1).mixMasteringLicenseInfos(mixMasteringLicenseInfos)
          .mixMasteringContacts(mixMasteringContacts).users(initCommUser).build();

      mixMasteringContacts.add(MixMasteringContact.builder().type(1).content("010-1234-1234").mixMasteringInfo(mixMasteringInfo).build());

      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder().licenseType(1).price(10000).servedType("mp3").period(0).draftCount(1).isAssign(true).isUseCommercial(true).isServeOriginFile(false).isOtherUseApproved(false).mixMasteringInfo(mixMasteringInfo).build());
      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder().licenseType(2).price(15000).servedType("mp3").period(0).draftCount(1).isAssign(true).isUseCommercial(true).isServeOriginFile(true).isOtherUseApproved(false).mixMasteringInfo(mixMasteringInfo).build());
      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder().licenseType(3).price(20000).servedType("mp3").period(0).draftCount(1).isAssign(true).isUseCommercial(true).isServeOriginFile(true).isOtherUseApproved(true).mixMasteringInfo(mixMasteringInfo).build());

      mixMasteringInfos.add(mixMasteringInfo);
    }
    albumArtRepository.saveAll(albumArtInfos);
    mixMasteringRepository.saveAll(mixMasteringInfos);

    //앨범아트 구매의뢰
    for (int i = 0; i < 20; i++) {// 앨범아트 구매의뢰 더미데이터 생성
      Random random = new Random();
      int randomValue = random.nextInt(100000 - 2000) + 2000;
      int randomValue2 = random.nextInt(20 - 1) + 1;
      UUID serviceId = albumArtRepository.findByServiceName("serviceName" + randomValue2).getId();

      AlbumArtRequest albumArtRequest = AlbumArtRequest.builder().albumArtInfo(albumArtRepository.findById(serviceId).get()).users(initCommUser).createAt(LocalDateTime.now()).build();

      albumArtRequestRepository.save(albumArtRequest);
    }

  }
}