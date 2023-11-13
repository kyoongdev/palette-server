package com.study.palette.config.dummydata;

import com.study.palette.common.enums.Contact;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtRequest;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.albumArt.repository.AlbumArtRequestRepository;
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
import com.study.palette.module.mixMastering.entity.MixMasteringContact;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.serviceProgress.repository.ServiceProgressInfoRepository;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.time.LocalDate;
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

    private final FilterMasterRepository filterMasterRepository;
    private final FilterInfoRepository filterInfoRepository;
    private final ArtistRepository artistRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlbumArtService albumArtService;
    private final AlbumArtRepository albumArtRepository;
    private final AlbumArtRequestRepository albumArtRequestRepository;
    private final ServiceProgressInfoRepository serviceProgressInfoRepository;
    private final MixMasteringRepository mixMasteringRepository;


    /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
    @Override
    public void run(ApplicationArguments args) throws Exception {

        /* 연관관계 확인 후 save */
        FilterMaster filterMaster1 = FilterMaster.builder()
                .code(1)
                .codeName("아티스트")
                .isUse(true)
                .createdAt(LocalDate.now())
                .userId("1234")
                .build();
        filterMasterRepository.save(filterMaster1);
        FilterMaster filterMaster2 = FilterMaster.builder()
                .code(2)
                .codeName("앨범아트판매유형")
                .isUse(true)
                .createdAt(LocalDate.now())
                .userId("1234")
                .build();
        filterMasterRepository.save(filterMaster2);

        List<FilterInfo> filterInfoList = new ArrayList<>();

        new FilterInfo(1, "작사", true, LocalDate.now(), "234234", filterMaster1);
        filterInfoList.add(new FilterInfo(1, "작사", true, LocalDate.now(), "234234", filterMaster1));
        filterInfoList.add(new FilterInfo(2, "피처링", true, LocalDate.now(), "234234", filterMaster1));
        filterInfoList.add(new FilterInfo(3, "가이드 녹음", true, LocalDate.now(), "234234", filterMaster1));
        filterInfoList.add(new FilterInfo(4, "악기 녹음", true, LocalDate.now(), "234234", filterMaster1));
        filterInfoList.add(new FilterInfo(5, "레코드 엔지니어", true, LocalDate.now(), "234234", filterMaster1));
        filterInfoList.add(new FilterInfo(6, "그 외 서비스", true, LocalDate.now(), "234234", filterMaster1));

        /*save 메소드 사용해 저장 */
        filterInfoRepository.saveAll(filterInfoList);

        filterInfoList = new ArrayList<>();

        filterInfoList.add(
            new FilterInfo(1, "사진편집", true, LocalDate.now(), "234234", filterMaster2));
        filterInfoList.add(
            new FilterInfo(2, "일러스트", true, LocalDate.now(), "234234", filterMaster2));
        filterInfoList.add(
            new FilterInfo(3, "그래픽아트", true, LocalDate.now(), "234234", filterMaster2));
        filterInfoList.add(
            new FilterInfo(4, "그외장르", true, LocalDate.now(), "234234", filterMaster2));

        /*save 메소드 사용해 저장 */
        filterInfoRepository.saveAll(filterInfoList);

        Users newUsers = usersRepository.save(
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
        artistInfo.setUsers(newUsers);

        /* 연관관계 확인 후 save*/
        artistRepository.save(artistInfo);

        List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseCreateRequestDtos = new ArrayList<>();
        List<AlbumArtContactCreateDto> AlbumArtContactCreateDtos = new ArrayList<>();

        Users newUsers2 = usersRepository.save(
            Users.builder()
                .role(Role.MEMBER)
                .email("tes11t@test")
                .password(passwordEncoder.encode("test1234"))
                .name("오득춘")
                .build());
        //앨범아트 더미데이터 생성
        for (int i = 0; i < 50; i++) {
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
            }

            for (int j = 0; j < 5; j++) {
                AlbumArtContactCreateDtos.add(//앨범아트 라이센스 생성
                        new AlbumArtContactCreateDto(
                                Contact.findContact(j + 1),
                                "content" + j
                        ));
            }

            AlbumArtCreateRequestDto albumArtCreateRequestDto = new AlbumArtCreateRequestDto(
                "serviceName" + i,
                "serviceExplain",
                2,
                "editInfo",
                albumArtLicenseCreateRequestDtos,
                AlbumArtContactCreateDtos,
                true
            );

            AlbumArtInfo albumArtInfo = albumArtCreateRequestDto.toEntity(newUsers2);
            List<AlbumArtLicenseInfo> licenses = albumArtCreateRequestDto.getAlbumArtLicenseInfo()
                .stream()
                .map(license -> AlbumArtLicenseInfo.from(license, albumArtInfo))
                .toList();
            albumArtInfo.setAlbumArtLicenseInfo(licenses);
            albumArtRepository.save(albumArtInfo);
            albumArtLicenseCreateRequestDtos.clear();
        }

        for (int i = 0; i < 20; i++) {// 앨범아트 구매의뢰 더미데이터 생성
            Random random = new Random();
            int randomValue = random.nextInt(100000 - 2000) + 2000;
            int randomValue2 = random.nextInt(20 - 1) + 1;
            UUID serviceId = albumArtRepository.findByServiceName("serviceName" + randomValue2).getId();

            AlbumArtRequest albumArtRequest = AlbumArtRequest.builder()
                .albumArtInfo(albumArtRepository.findById(serviceId).get())
                .users(newUsers2)
                .createAt(LocalDate.now())
                .build();

            albumArtRequestRepository.save(albumArtRequest);
        }

        Users newUsers3 = usersRepository.save(
            Users.builder()
                .role(Role.MUSICIAN)
                .email("tes11t@test222")
                .password(passwordEncoder.encode("test1234"))
                .name("용준팍")
                .build());

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
                .users(newUsers3)
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