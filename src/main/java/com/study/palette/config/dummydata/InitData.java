package com.study.palette.config.dummydata;

import com.study.palette.common.enums.Contact;
import com.study.palette.common.enums.recording.Address1;
import com.study.palette.common.enums.recording.Address2;
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
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local")
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
    Users initCommUser = usersRepository.save(
        Users.builder()
            .role(Role.MUSICIAN)
            .email("test@test")
            .password(passwordEncoder.encode("test1234"))
            .name("홍길동")
            .build());

    /* Artist 관련 클래스 예시 */
    ArtistFile artistFile = new ArtistFile();
    ArtistLicenseInfo artistLicenseInfo = new ArtistLicenseInfo();
    ArtistReview artistReview = new ArtistReview();

    ArtistInfo artistInfo = new ArtistInfo();
    artistInfo.setArtistFile(artistFile);
    artistInfo.setArtistLicenseInfo(artistLicenseInfo);
    artistInfo.setArtistReview(artistReview);
    artistInfo.setUsers(initCommUser);

    /* 연관관계 확인 후 save*/
    artistRepository.save(artistInfo);

    //albumart
    List<AlbumArtInfo> albumArtInfos = new ArrayList<>();
    List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseCreateRequestDtos = new ArrayList<>();
    List<AlbumArtContactCreateDto> AlbumArtContactCreateDtos = new ArrayList<>();
    List<AlbumArtFileCreateRequestDto> albumArtFileCreateRequestDtos = new ArrayList<>();
    //recording
    List<RecordingInfo> recordingInfos = new ArrayList<>();
    List<RecordingLicenseInfoCreateRequestDto> recordingLicenseInfoCreateRequestDtos = new ArrayList<>();
    List<RecordingFileCreateRequestDto> recordingFileCreateRequestDtos = new ArrayList<>();
    //mixMastering
    List<MixMasteringInfo> mixMasteringInfos = new ArrayList<>();

    //앨범아트 더미데이터 생성
    for (int i = 0; i < 50; i++) {
      //albumart
      albumArtFileCreateRequestDtos.add(//앨범아트 파일 생성
          new AlbumArtFileCreateRequestDto(
              "www.test.com",
              "testfilenale",
              "uploadfilname",
              1000,
              "jpg",
              true
          ));
      recordingFileCreateRequestDtos.add(//레코딩 파일 생성
          new RecordingFileCreateRequestDto(
              "www.test.com",
              "testfilenale",
              "uploadfilname",
              1000,
              "mp3",
              true
          ));
      for (int j = 0; j < 3; j++) {
        albumArtLicenseCreateRequestDtos.add(//앨범아트 라이센스 생성
            new AlbumArtLicenseInfoCreateRequestDto(
                10,
                1000,
                "servedFile" + i,
                3,
                null,
                3,
                true,
                true,
                true,
                true
            ));
        albumArtFileCreateRequestDtos.add(//앨범아트 파일 생성
            new AlbumArtFileCreateRequestDto(
                "www.test.com",
                "testfilenale",
                "uploadfilname",
                1000,
                "jpg",
                false
            ));
        recordingFileCreateRequestDtos.add(//레코딩 파일 생성
            new RecordingFileCreateRequestDto(
                "www.test.com",
                "testfilenale",
                "uploadfilname",
                1000,
                "mp3",
                false
            ));
        recordingLicenseInfoCreateRequestDtos.add(//레코딩 라이센스 생성
            new RecordingLicenseInfoCreateRequestDto(
                1,
                1000,
                1
            ));
      }
      for (int j = 0; j < 5; j++) {
        AlbumArtContactCreateDtos.add(//앨범아트 연락수단 생성
            new AlbumArtContactCreateDto(
                Contact.findContact(j + 1),
                "content" + j
            ));
      }
      //albumart 생성
      AlbumArtCreateRequestDto albumArtCreateRequestDto = new AlbumArtCreateRequestDto(
          "serviceName" + i,
          "serviceExplain",
          2,
          "editInfo",
          albumArtLicenseCreateRequestDtos,
          AlbumArtContactCreateDtos,
          albumArtFileCreateRequestDtos,
          true
      );

      //recording 생성
      RecordingCreateRequestDto recordingCreateRequestDto = new RecordingCreateRequestDto(
          "serviceName" + i,
          "studioName" + i,
          Address1.of(1),
          Address2.of(1, 1),
          true,
          "www.test.com",
          "serviceExplain",
          recordingFileCreateRequestDtos,
          recordingLicenseInfoCreateRequestDtos
      );

      AlbumArtInfo albumArtInfo = albumArtCreateRequestDto.toEntity(initCommUser);
      List<AlbumArtLicenseInfo> licenses = albumArtCreateRequestDto.getAlbumArtLicenseInfo()
          .stream()
          .map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo))
          .toList();
      List<AlbumArtContact> contacts = albumArtCreateRequestDto.getAlbumArtContact()
          .stream()
          .map(contact -> AlbumArtContact.from(contact, albumArtInfo))
          .toList();
      List<AlbumArtFile> files = albumArtCreateRequestDto.getAlbumArtFiles()
          .stream()
          .map(file -> AlbumArtFile.from(file, albumArtInfo))
          .toList();
      albumArtInfo.setAlbumArtLicenseInfo(licenses);
      albumArtInfo.setAlbumArtContact(contacts);
      albumArtInfo.setAlbumArtFiles(files);
      albumArtInfos.add(albumArtInfo);
      albumArtLicenseCreateRequestDtos.clear();
      AlbumArtContactCreateDtos.clear();
      albumArtFileCreateRequestDtos.clear();

      RecordingInfo recordingInfo = recordingCreateRequestDto.toEntity(initCommUser);
      List<RecordingFile> recordingFiles = recordingCreateRequestDto.getRecordingFile()
          .stream()
          .map(file -> RecordingFile.from(file, recordingInfo))
          .toList();
      List<RecordingLicenseInfo> recordingLicenseInfo = recordingCreateRequestDto.getRecordingLicenseInfo()
          .stream()
          .map(license -> RecordingLicenseInfo.from(license, recordingInfo))
          .toList();
      recordingInfo.setRecordingLicenseInfo(recordingLicenseInfo);
      recordingInfo.setRecordingFile(recordingFiles);
      recordingInfos.add(recordingInfo);
      recordingFileCreateRequestDtos.clear();
      recordingLicenseInfoCreateRequestDtos.clear();

      //mixMastering
      List<MixMasteringContact> mixMasteringContacts = new ArrayList<>();
      List<MixMasteringLicenseInfo> mixMasteringLicenseInfos = new ArrayList<>();

      MixMasteringInfo mixMasteringInfo = MixMasteringInfo.builder()
          .serviceName("믹스 마스터링" + Integer.toString(i))
          .beforeJobMusic("")
          .afterJobMusic("")
          .serviceExplain("")
          .editInfo("")
          .serviceStatus(true)
          .genre((i % 5) + 1)
          .mixMasteringLicenseInfos(mixMasteringLicenseInfos)
          .mixMasteringContacts(mixMasteringContacts)
          .users(initCommUser)
          .build();

      mixMasteringContacts.add(MixMasteringContact.builder()
          .type(1)
          .content("010-1234-1234")
          .mixMasteringInfo(mixMasteringInfo)
          .build());

      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder()
          .licenseType(1)
          .price(10000)
          .servedType("mp3")
          .period(0)
          .draftCount(1)
          .isAssign(true)
          .isUseCommercial(true)
          .isServeOriginFile(false)
          .isOtherUseApproved(false)
          .mixMasteringInfo(mixMasteringInfo)
          .build());
      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder()
          .licenseType(2)
          .price(15000)
          .servedType("mp3")
          .period(0)
          .draftCount(1)
          .isAssign(true)
          .isUseCommercial(true)
          .isServeOriginFile(true)
          .isOtherUseApproved(false)
          .mixMasteringInfo(mixMasteringInfo)
          .build());
      mixMasteringLicenseInfos.add(MixMasteringLicenseInfo.builder()
          .licenseType(3)
          .price(20000)
          .servedType("mp3")
          .period(0)
          .draftCount(1)
          .isAssign(true)
          .isUseCommercial(true)
          .isServeOriginFile(true)
          .isOtherUseApproved(true)
          .mixMasteringInfo(mixMasteringInfo)
          .build());

      mixMasteringInfos.add(mixMasteringInfo);
    }
    albumArtRepository.saveAll(albumArtInfos);
    recordingRepository.saveAll(recordingInfos);
    mixMasteringRepository.saveAll(mixMasteringInfos);

    //앨범아트 구매의뢰
    for (int i = 0; i < 20; i++) {// 앨범아트 구매의뢰 더미데이터 생성
      Random random = new Random();
      int randomValue = random.nextInt(100000 - 2000) + 2000;
      int randomValue2 = random.nextInt(20 - 1) + 1;
      UUID serviceId = albumArtRepository.findByServiceName("serviceName" + randomValue2).getId();

      AlbumArtRequest albumArtRequest = AlbumArtRequest.builder()
          .albumArtInfo(albumArtRepository.findById(serviceId).get())
          .users(initCommUser)
          .createAt(LocalDateTime.now())
          .build();

      albumArtRequestRepository.save(albumArtRequest);
    }

  }
}