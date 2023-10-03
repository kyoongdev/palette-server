package com.study.palette.module.albumArt.dto;

import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtReview;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtCreateDto {
    @NotBlank(message = "서비스명을 입력해주세요.")
    private String serviceName;
    @NotBlank(message = "서비스 설명을 입력해주세요.")
    private String serviceExplain;
    @NotBlank(message = "판매 유형을 입력해주세요.")
    private String salesType;
    @NotBlank(message = "수정관련 안내를 입력해주세요.")
    private String editInfo;
    @NotBlank(message = "라이센스 정보를 입력해주세요.")
    private List<AlbumArtLicenseCreateDto> albumArtLicenseInfo = new ArrayList<>();

    //TODO 파일 구현 후 추가
//    private List<MultipartFile> albumArtFiles = new ArrayList<>();

    private boolean serviceStatus;


//public class AlbumArtInfo {
//
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(length = 24)
//    private String id;
//
//    @Column(length = 50)
//    private String serviceName;
//
//    @Column(length = 1000)
//    private String serviceExplain;
//
//    @ManyToOne
//    @JoinColumn(name = "salesType")
//    private FilterInfo filterInfo;
//
//    @Column(length = 1000)
//    private String editInfo;
//
//    private boolean serviceStatus;
//
//    private LocalDate createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtFile> albumArtFile;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtReview> albumArtReview;
//
//    @OneToMany(mappedBy = "albumArtInfo", fetch = FetchType.LAZY)
//    private List<AlbumArtLicenseInfo> albumArtLicenseInfo;

}