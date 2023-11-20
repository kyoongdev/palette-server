package com.study.palette.module.artist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.artist.dto.CreateArtistContactDto;
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
public class ArtistContact {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private int type;

  private String content;

  private LocalDateTime createAt;

  @ManyToOne
  @JoinColumn(name = "artistInfoId")
  @JsonIgnore
  private ArtistInfo artistInfo;


  public static ArtistContact from(CreateArtistContactDto artistContactDto,
      ArtistInfo artistInfo) {
    return builder()
        .type(artistContactDto.getType().getContact())
        .content(artistContactDto.getContent())
        .artistInfo(artistInfo).build();

  }
}
