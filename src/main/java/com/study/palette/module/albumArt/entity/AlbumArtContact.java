package com.study.palette.module.albumArt.entity;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
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
public class AlbumArtContact {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private Contact type;

  private String content;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "albumArtInfoId")
  private AlbumArtInfo albumArtInfo;

  public static AlbumArtContact from(AlbumArtContactCreateDto dto, AlbumArtInfo albumArtInfo) {
    return AlbumArtContact.builder()
        .type(Contact.findContact(dto.getType()))
        .content(dto.getContent())
        .albumArtInfo(albumArtInfo)
        .build();
  }

  public static AlbumArtContact from(AlbumArtContactDto dto, AlbumArtInfo albumArtInfo) {
    return AlbumArtContact.builder()
        .type(Contact.findContact(dto.getType()))
        .content(dto.getContent())
        .albumArtInfo(albumArtInfo)
        .build();
  }
}
