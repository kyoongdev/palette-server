package com.study.palette.config.dummydata;

import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.filter.entity.FilterMaster;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import com.study.palette.module.filter.repository.FilterMasterRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local")
public class FilterInitData implements ApplicationRunner {

  private final FilterMasterRepository filterMasterRepository;
  private final FilterInfoRepository filterInfoRepository;


  /* 더미데이터 생성 시 new 연산자를 사용하거나 builder 패턴을 사용해서 데이터를 만들어준 뒤 reposiotry에 save (최초 한번만 실행 후 주석 처리) 추후 문제 처리 하겠습니다.*/
  @Override
  public void run(ApplicationArguments args) throws Exception {
    /* 연관관계 확인 후 save */
    FilterMaster artistFilter = FilterMaster.builder()
        .code(1)
        .codeName("아티스트")
        .isUse(true)
        .createdAt(LocalDate.now())
        .userId("1234")
        .build();
    filterMasterRepository.save(artistFilter);
    FilterMaster albumArtFilter = FilterMaster.builder()
        .code(2)
        .codeName("앨범아트판매유형")
        .isUse(true)
        .createdAt(LocalDate.now())
        .userId("1234")
        .build();
    filterMasterRepository.save(albumArtFilter);

    List<FilterInfo> filterInfoList = new ArrayList<>();
    new FilterInfo(1, "작사", true, LocalDate.now(), "234234", artistFilter);
    filterInfoList.add(new FilterInfo(1, "작사", true, LocalDate.now(), "234234", artistFilter));
    filterInfoList.add(new FilterInfo(2, "피처링", true, LocalDate.now(), "234234", artistFilter));
    filterInfoList.add(new FilterInfo(3, "가이드 녹음", true, LocalDate.now(), "234234", artistFilter));
    filterInfoList.add(new FilterInfo(4, "악기 녹음", true, LocalDate.now(), "234234", artistFilter));
    filterInfoList.add(new FilterInfo(5, "레코드 엔지니어", true, LocalDate.now(), "234234", artistFilter));
    filterInfoList.add(new FilterInfo(6, "그 외 서비스", true, LocalDate.now(), "234234", artistFilter));
    /*save 메소드 사용해 저장 */
    filterInfoRepository.saveAll(filterInfoList);

    filterInfoList = new ArrayList<>();
    filterInfoList.add(new FilterInfo(1, "사진편집", true, LocalDate.now(), "234234", albumArtFilter));
    filterInfoList.add(new FilterInfo(2, "일러스트", true, LocalDate.now(), "234234", albumArtFilter));
    filterInfoList.add(new FilterInfo(3, "그래픽아트", true, LocalDate.now(), "234234", albumArtFilter));
    filterInfoList.add(new FilterInfo(4, "그외장르", true, LocalDate.now(), "234234", albumArtFilter));

    /*save 메소드 사용해 저장 */
    filterInfoRepository.saveAll(filterInfoList);
  }
}