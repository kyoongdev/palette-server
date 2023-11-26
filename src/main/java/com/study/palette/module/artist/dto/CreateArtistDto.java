package com.study.palette.module.artist.dto;


import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistLicenseDto;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistDto {

  @NotBlank(message = "서비스명을 입력해주세요.")
  private String serviceName;

  @NotBlank(message = "서비스 설명을 입력해주세요.")
  private String serviceInfo;

  @NotBlank(message = "서비스 수정사항을 입력해주세요.")
  private String editInfo;


  private int salesType;

  private boolean serviceStatus;

  private LocalDate createdAt;

  private List<CreateArtistFileDto> artistFileDto;

  @NotNull(message = "라이센스 정보를 입력해주세요.")
  private List<CreateArtistLicenseDto> artistLicenseInfo;

  @NotNull(message = "연락수단을 하나이상 기재해 주세요.")
  private List<CreateArtistContactDto> artistContactDto;

  public ArtistInfo toEntity(Users users) {
    return ArtistInfo.builder()
        .serviceName(this.getServiceName())
        .salesType(this.getSalesType())
        .serviceInfo(this.getServiceInfo())
        .serviceStatus(this.isServiceStatus())
        .createdAt(this.getCreatedAt())
        .artistFile(new ArrayList<>())
        .artistReview(new ArrayList<>())
        .artistContact(new ArrayList<>())
        .artistLicenseInfo(new ArrayList<>())
        .users(users).build();
  }


}
