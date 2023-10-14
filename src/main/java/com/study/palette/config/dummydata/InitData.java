package com.study.palette.config.dummydata;

import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.filter.entity.FilterMaster;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import com.study.palette.module.filter.repository.FilterMasterRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Profile("local")
public class InitData implements ApplicationRunner {

    private final FilterMasterRepository filterMasterRepository;
    private final FilterInfoRepository filterInfoRepository;

    private final ArtistRepository artistRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
    @Override
    public void run(ApplicationArguments args) throws Exception {

        /* 연관관계 확인 후 save */
        FilterMaster filterMaster = new FilterMaster(1, "아티스트", true, LocalDate.now(), "234234");
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

        Optional<User> findUser = userRepository.findByEmail("test@test");

        if(!findUser.isPresent()) {

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

    }
}
