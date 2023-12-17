package com.study.palette.config.dummydata;

import com.study.palette.common.enums.Contact;
import com.study.palette.module.artist.dto.CreateArtistContactDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistLicenseDto;
import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    }
  }
}