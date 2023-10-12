package com.study.palette.module.artist.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistDetailResponseDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.UpdateArtistDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.artist.repository.ArtistReviewRepository;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.filter.repository.FilterInfoRepository;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistService {

  private ArtistRepository artistRepository;

  private ArtistReviewRepository artistReviewRepository;
  private FilterInfoRepository filterInfoRepository;

  private UserRepository userRepository;


  @Autowired
  public ArtistService(ArtistRepository artistRepository, FilterInfoRepository filterInfoRepository, ArtistReviewRepository artistReviewRepository, UserRepository userRepository) {
    this.filterInfoRepository = filterInfoRepository;
    this.artistRepository = artistRepository;
    this.artistReviewRepository = artistReviewRepository;
    this.userRepository = userRepository;

  }

  /* artistInfo artistInfo 필터 정보 조회*/
  public PaginationDto<ArtistResponseDto> findArtists(Pageable pageable) {
    //페이지네이션을 이용해서 정보를 조회하기 위해서는
    //크게
    // count: 해당 요청에 맞는 "전체 데이터 개수"
    // page : 현재 요청의 "페이지 번호"
    // limit : 한 페이지에 보여줄 "데이터 개수"
    //가 필요합니다.
    Long count = artistRepository.count();
    List<ArtistResponseDto> artists = artistRepository.findAll(pageable).stream().map(ArtistResponseDto::new).collect(Collectors.toList());

    PaginationDto<ArtistResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), artists);


    return row;
  }

  public PaginationDto<ArtistResponseDto> findArtistsFilter(FindArtistsQuery query) {

    Long count = artistRepository.countByFilterInfo_Code(query.getCode());

    Sort.Order sortOption;

    if(query.getSort().equals("id")) {

        sortOption = Sort.Order.asc(query.getSort());
    } else {
        sortOption = Sort.Order.desc("artistReview." + query.getSort());
    }

    Pageable pageable = query.toPageable(Sort.by(sortOption));

    List<ArtistResponseDto> artists = artistRepository.findAllByFilterInfo_Code(query.getCode(), pageable).stream().map(ArtistResponseDto::new).collect(Collectors.toList());

    PaginationDto<ArtistResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), artists);

    return row;

  }


  public ArtistDetailResponseDto findArtistsDetail(String no) {


    ArtistInfo artists = artistRepository.findById(no).orElse(null);

    ArtistDetailResponseDto artistDetail = ArtistDetailResponseDto.toEntity(artists);

    /* review 조회 중 */
//    int artistReviewRatingSum = 0;
//    int artistReviewRatingCount = 0;
//
//    for(int i = 0; i < artistDetail.getArtistReview().size(); i++) {
//
//      artistReviewRatingSum += artistDetail.getArtistReview().get(i).getRating();
//
//      artistReviewRatingCount++;
//
//    }
//
//    int artistReviewAverage = artistReviewRatingSum / artistReviewRatingCount;
//
//    artistDetail.setReviewAverage(artistReviewAverage);

    return artistDetail;
  }


  @Transactional
  public ResponseWithIdDto createArtist(CreateArtistDto data) {

    /*User 정보 가져와서 artistInfo에 추가*/
    User user = userRepository.findByEmail(data.getUserEmail()).orElse(null);

    /* ArtistFile 정보 */
    String originFileName = "";
    String uploadFileName = "";
    int uploadFileSize = 0;
    String suffix = "";
    boolean isThumbNail;
    boolean isUse;
    LocalDate createdAt = LocalDate.now();
    UUID uuid = UUID.randomUUID();
    List<ArtistFile> artistFiles = new ArrayList<>();

    for(int i = 0; i < data.getArtistFileDto().size(); i++) {

        originFileName = data.getArtistFileDto().get(i).getOriginFileName();
        uploadFileName = uuid.toString() + data.getArtistFileDto().get(i).getOriginFileName();
        uploadFileSize = data.getArtistFileDto().get(i).getUploadFileSize();
        suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        isThumbNail = data.getArtistFileDto().get(i).isThumbnail();
        isUse = data.getArtistFileDto().get(i).isUse();

        ArtistFile artistFile = ArtistFile.builder()
                .originFileName(originFileName)
                .uploadFileName(uploadFileName)
                .uploadFileSize(uploadFileSize)
                .suffix(suffix)
                .createdAt(createdAt)
                .isThumbnail(isThumbNail)
                .isUse(isUse).build();

        artistFiles.add(artistFile);
    }

    /* ArtistLicensInfo 정보 */
    List<ArtistLicenseInfo> artistLicenseInfoList = new ArrayList<>();

    for(int i = 0; i < data.getArtistLicenseInfo().size(); i++) {
      ArtistLicenseInfo artistLicenseInfo = ArtistLicenseInfo.builder()
              .licenseType(data.getArtistLicenseInfo().get(i).getLicenseType())
              .price(data.getArtistLicenseInfo().get(i).getPrice())
              .serveFile(data.getArtistLicenseInfo().get(i).getServeFile())
              .updateCount(data.getArtistLicenseInfo().get(i).getUpdateCount())
              .period(data.getArtistLicenseInfo().get(i).getPeriod())
              .isAssign(data.getArtistLicenseInfo().get(i).isAssign())
              .isUseCommerical(data.getArtistLicenseInfo().get(i).isUseCommerical())
              .isServeOriginFile(data.getArtistLicenseInfo().get(i).isServeOriginFile()).build();

              artistLicenseInfoList.add(artistLicenseInfo);
    }


    ArtistInfo createArtist = ArtistInfo.builder()
            .serviceName(data.getServiceName())
            .serviceInfo(data.getServiceInfo())
            .editInfo(data.getEditInfo())
            .filterInfo(data.getSalesType())
            .serviceStatus(data.isServiceStatus())
            .user(user)
            .artistFile(new ArrayList<>())
            .artistLicenseInfo(new ArrayList<>()).build();


    for(int i = 0; i < artistFiles.size(); i++) {

      createArtist.setArtistFile(artistFiles.get(i));

    }

    for(int i = 0; i < artistLicenseInfoList.size(); i++) {
      createArtist.setArtistLicenseInfo(artistLicenseInfoList.get(i));
    }

    artistRepository.save(createArtist);

    return ResponseWithIdDto.builder().id(createArtist.getId()).build();

  }

  public ResponseWithIdDto updateArtist(UpdateArtistDto data) {

    User user = userRepository.findByEmail(data.getUserEmail()).orElse(null);

    /* ArtistFile 정보 */
    String originFileName = "";
    String uploadFileName = "";
    int uploadFileSize = 0;
    String suffix = "";
    boolean isThumbNail;
    boolean isUse;
    LocalDate createdAt = LocalDate.now();
    UUID uuid = UUID.randomUUID();
    List<ArtistFile> artistFiles = new ArrayList<>();

    for(int i = 0; i < data.getArtistFileDto().size(); i++) {

      originFileName = data.getArtistFileDto().get(i).getOriginFileName();
      uploadFileName = uuid.toString() + data.getArtistFileDto().get(i).getOriginFileName();
      uploadFileSize = data.getArtistFileDto().get(i).getUploadFileSize();
      suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
      isThumbNail = data.getArtistFileDto().get(i).isThumbnail();
      isUse = data.getArtistFileDto().get(i).isUse();

      ArtistFile artistFile = ArtistFile.builder()
              .originFileName(originFileName)
              .uploadFileName(uploadFileName)
              .uploadFileSize(uploadFileSize)
              .suffix(suffix)
              .createdAt(createdAt)
              .isThumbnail(isThumbNail)
              .isUse(isUse).build();

      artistFiles.add(artistFile);
    }

    /* ArtistLicensInfo 정보 */
    List<ArtistLicenseInfo> artistLicenseInfoList = new ArrayList<>();

    for(int i = 0; i < data.getArtistLicenseInfo().size(); i++) {
      ArtistLicenseInfo artistLicenseInfo = ArtistLicenseInfo.builder()
              .licenseType(data.getArtistLicenseInfo().get(i).getLicenseType())
              .price(data.getArtistLicenseInfo().get(i).getPrice())
              .serveFile(data.getArtistLicenseInfo().get(i).getServeFile())
              .updateCount(data.getArtistLicenseInfo().get(i).getUpdateCount())
              .period(data.getArtistLicenseInfo().get(i).getPeriod())
              .isAssign(data.getArtistLicenseInfo().get(i).isAssign())
              .isUseCommerical(data.getArtistLicenseInfo().get(i).isUseCommerical())
              .isServeOriginFile(data.getArtistLicenseInfo().get(i).isServeOriginFile()).build();

      artistLicenseInfoList.add(artistLicenseInfo);
    }


    ArtistInfo updateArtistInfo = artistRepository.findById(data.getId()).orElse(null);
    updateArtistInfo.getArtistFile().clear();
    updateArtistInfo.getArtistLicenseInfo().clear();

    updateArtistInfo.modifyArtistInfo(data.getServiceName()
                                      ,data.getServiceInfo()
                                      ,data.getEditInfo()
                                      ,data.getSalesType()
                                      ,data.isServiceStatus()
                                      ,createdAt
                                      ,updateArtistInfo.getArtistFile()
                                      ,updateArtistInfo.getArtistLicenseInfo());


    for(int i = 0; i < artistFiles.size(); i++) {

      updateArtistInfo.setArtistFile(artistFiles.get(i));

    }

    for(int i = 0; i < artistLicenseInfoList.size(); i++) {
      updateArtistInfo.setArtistLicenseInfo(artistLicenseInfoList.get(i));
    }

    artistRepository.save(updateArtistInfo);

    return ResponseWithIdDto.builder().id(updateArtistInfo.getId()).build();
  }


  public Boolean artistDelete(String id) {

    artistRepository.deleteById(id);

    return true;
  }



}
