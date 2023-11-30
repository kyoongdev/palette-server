package com.study.palette.module.adminService.dto;


import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import com.study.palette.module.albumArt.exception.AlbumArtErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindAdminServiceQuery extends PageDto {

  @Schema(description = "전문장르", type = "string", allowableValues = {"ALL", "ENGINEERING",
      "NOT_ENGINEERING"})
  public MixMasteringGenre genre;

  @Schema(description = "정렬", defaultValue = "NEW", type = "string", allowableValues = {"NEW",
      "POPULAR"})
  private CustomSort sort;

  public OrderSpecifier<?>[] getSort() {
    if (this.sort == null) {
      throw new CustomException(AlbumArtErrorCode.ALBUM_ART_NOT_SORT);
    }

    if (this.sort == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QAlbumArtRequest.albumArtRequest.id.count().desc()};
    } else { // 신규등록
      return new OrderSpecifier[]{QAlbumArtInfo.albumArtInfo.createdAt.desc()};
    }
  }
}
