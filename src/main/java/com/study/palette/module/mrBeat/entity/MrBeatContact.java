package com.study.palette.module.mrBeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.mrBeat.dto.CreateMrBeatContactDto;
import java.time.LocalDateTime;
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
public class MrBeatContact {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private int type;

  private String content;

  private LocalDateTime createAt;

  @ManyToOne
  @JoinColumn(name = "mrBeatInfoId")
  @JsonIgnore
  private MrBeatInfo mrBeatInfo;


  public static MrBeatContact from(CreateMrBeatContactDto mrBeatContactDto,
      MrBeatInfo mrBeatInfo) {
    return builder()
        .type(mrBeatContactDto.getType().getContact())
        .content(mrBeatContactDto.getContent())
        .mrBeatInfo(mrBeatInfo).build();

  }
}
