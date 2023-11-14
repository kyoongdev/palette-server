package com.study.palette.module.mixMastering.entity;


import com.study.palette.module.mixMastering.dto.contact.CreateMixMasteringContactDto;
import com.study.palette.module.mixMastering.dto.contact.MixMasteringContactDto;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MixMasteringContact {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private int type;

  private String content;

  @ManyToOne
  @JoinColumn(name = "mixMasteringInfoId")
  private MixMasteringInfo mixMasteringInfo;

  public static MixMasteringContact from(MixMasteringContactDto mixMasteringContactDto, MixMasteringInfo mixMasteringInfo) {
    return MixMasteringContact.builder()
        .type(mixMasteringContactDto.getType().getContact())
        .content(mixMasteringContactDto.getContent())
        .mixMasteringInfo(mixMasteringInfo)
        .build();
  }

  public static MixMasteringContact from(CreateMixMasteringContactDto mixMasteringContactDto, MixMasteringInfo mixMasteringInfo) {
    return MixMasteringContact.builder()
        .type(mixMasteringContactDto.getType().getContact())
        .content(mixMasteringContactDto.getContent())
        .mixMasteringInfo(mixMasteringInfo)
        .build();
  }
}
