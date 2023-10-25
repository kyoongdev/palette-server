package com.study.palette.config.dummydata;

import com.study.palette.common.enums.albumArt.AlbumArtServiceType;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.filter.entity.FilterMaster;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import com.study.palette.module.filter.repository.FilterMasterRepository;
import com.study.palette.module.serviceProgress.entity.ServiceProgressInfo;
import com.study.palette.module.serviceProgress.repository.ServiceProgressInfoRepository;
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Profile("local")
public class InitData implements ApplicationRunner {

    private final FilterMasterRepository filterMasterRepository;
    private final FilterInfoRepository filterInfoRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlbumArtService albumArtService;
    private final AlbumArtRepository albumArtRepository;
    private final ServiceProgressInfoRepository serviceProgressInfoRepository;
    private final MixMasteringRepository mixMasteringRepository;


    /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
    @Override
    public void run(ApplicationArguments args) throws Exception {

        /* 연관관계 확인 후 save */
        FilterMaster filterMaster = new FilterMaster(1, "아티스트", true, LocalDate.now(), "234234");
        filterMasterRepository.save(filterMaster);
        filterMaster = new FilterMaster(2, "앨범아트 판매유형", true, LocalDate.now(), "234234");
        filterMasterRepository.save(filterMaster);

        List<FilterInfo> filterInfoList = new ArrayList<>();

        new FilterInfo(1, "작사", true, LocalDate.now(), "234234", filterMaster);
        filterInfoList.add(new FilterInfo(1, "작사", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(2, "피처링", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(3, "가이드 녹음", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(4, "악기 녹음", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(5, "레코드 엔지니어", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(6, "그 외 서비스", true, LocalDate.now(), "234234", filterMaster));
        /*save 메소드 사용해 저장 */
        filterInfoRepository.saveAll(filterInfoList);

        filterInfoList = new ArrayList<>();

        filterInfoList.add(new FilterInfo(7, "사진편집", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(8, "일러스트", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(9, "그래픽아트", true, LocalDate.now(), "234234", filterMaster));
        filterInfoList.add(new FilterInfo(10, "그외장르", true, LocalDate.now(), "234234", filterMaster));

        /*save 메소드 사용해 저장 */
        filterInfoRepository.saveAll(filterInfoList);

        Optional<User> findUser = userRepository.findByEmail("test@test");

        if (!findUser.isPresent()) {

            User newUser = userRepository.save(
                    User.builder()
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
            artistInfo.setUser(newUser);

            /* 연관관계 확인 후 save*/
            artistRepository.save(artistInfo);
        }

        List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseCreateRequestDtos = new ArrayList<>();

        User newUser2 = userRepository.save(
                User.builder()
                        .role(Role.MEMBER)
                        .email("tes11t@test")
                        .password(passwordEncoder.encode("test1234"))
                        .name("오득춘")
                        .build());

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 3; j++) {
                albumArtLicenseCreateRequestDtos.add(
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
            }

            AlbumArtCreateRequestDto albumArtCreateRequestDto = new AlbumArtCreateRequestDto(
                    "serviceName" + i,
                    "serviceExplain",
                    10,
                    "editInfo",
                    albumArtLicenseCreateRequestDtos,
                    true
            );

            AlbumArtInfo albumArtInfo = albumArtCreateRequestDto.toEntity(newUser2);
            List<AlbumArtLicenseInfo> licenses = albumArtCreateRequestDto.getAlbumArtLicenseInfo().stream()
                    .map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo))
                    .toList();
            albumArtInfo.setAlbumArtLicenseInfo(licenses);
            albumArtRepository.save(albumArtInfo);
            albumArtLicenseCreateRequestDtos.clear();
        }

        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int randomValue = random.nextInt(100000 - 2000) + 2000;
            int randomValue2 = random.nextInt(20 - 1) + 1;
            UUID serviceId = albumArtRepository.findByServiceName("serviceName" + randomValue2).getId();
            AlbumArtServiceType type = AlbumArtServiceType.ALBUM_ART;

            ServiceProgressInfo serviceProgressInfo = ServiceProgressInfo.builder()
                    .serviceType(type)
                    .serviceId(serviceId)
                    .licenseType(1)
                    .price(randomValue)
                    .startDate(LocalDateTime.now())
                    .dueDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now())
                    .workProgress(1)
                    .isComplete(true)
                    .completeComment("완료")
                    .status(true)
                    .refundComment(null)
                    .createdAt(LocalDateTime.now())
                    .serviceProgressFile(null)
                    .build();

            serviceProgressInfoRepository.save(serviceProgressInfo);
        }
        User artist = findUser.get();

        List<MixMasteringInfo> mixMasteringInfos = new ArrayList<>();

        for (int i = 1; i < 50; i++) {

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
                    .user(artist)
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

        mixMasteringRepository.saveAll(mixMasteringInfos);
    }
}