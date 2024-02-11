package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.musician.ApprovalType;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.recording.entity.RecordingInfo;
import lombok.Data;

@Data
public class ApprovingServiceDetailResponseDto {

  private String id;
  private int code;
  private String refusalReason;

  public ApprovingServiceDetailResponseDto(MrBeatInfo mrBeatInfo) {
    this.id = mrBeatInfo.getId().toString();
    this.code = ApprovalType.findMarketApprovalType(mrBeatInfo.getApprovalStatus().getCode()).getCode();
    this.refusalReason = mrBeatInfo.getRefusalReason();
  }

  public ApprovingServiceDetailResponseDto(ArtistInfo artistInfo) {
    this.id = artistInfo.getId().toString();
    this.code = ApprovalType.findMarketApprovalType(artistInfo.getApprovalStatus().getCode()).getCode();
    this.refusalReason = artistInfo.getRefusalReason();
  }

  public ApprovingServiceDetailResponseDto(RecordingInfo recordingInfo) {
    this.id = recordingInfo.getId().toString();
    this.code = ApprovalType.findMarketApprovalType(recordingInfo.getApprovalStatus().getCode()).getCode();
    this.refusalReason = recordingInfo.getRefusalReason();
  }

  public ApprovingServiceDetailResponseDto(MixMasteringInfo mixMasteringInfo) {
    this.id = mixMasteringInfo.getId().toString();
    this.code = ApprovalType.findMarketApprovalType(mixMasteringInfo.getApprovalStatus().getCode()).getCode();
    this.refusalReason = mixMasteringInfo.getRefusalReason();
  }

  public ApprovingServiceDetailResponseDto(AlbumArtInfo albumArtInfo) {
    this.id = albumArtInfo.getId().toString();
    this.code = ApprovalType.findMarketApprovalType(albumArtInfo.getApprovalStatus().getCode()).getCode();
    this.refusalReason = albumArtInfo.getRefusalReason();
  }


}

