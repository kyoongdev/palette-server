package com.study.palette.module.albumArt.service;


import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AlbumArtConditions extends PageDto {

  private AlbumArtSaleType saleType;
  private CustomSort customSort;

  public OrderSpecifier<?>[] getSort() {
    if (this.customSort == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QAlbumArtRequest.albumArtRequest.id.count().desc()};
    } else {
      return new OrderSpecifier[]{QAlbumArtInfo.albumArtInfo.createdAt.desc()};
    }
  }
}