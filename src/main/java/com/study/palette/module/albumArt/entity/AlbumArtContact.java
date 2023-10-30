package com.study.palette.module.albumArt.entity;


import com.study.palette.common.enums.Contact;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


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

    private int type;

    private String content;

    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    private AlbumArtInfo albumArtInfo;

    public static AlbumArtContact from(AlbumArtContactCreateDto dto, AlbumArtInfo albumArtInfo) {
        return AlbumArtContact.builder()
                .type(dto.getType().getContact())
                .content(dto.getContent())
                .albumArtInfo(albumArtInfo)
                .build();
    }

    public static AlbumArtContact from(AlbumArtContactDto dto, AlbumArtInfo albumArtInfo) {
        return AlbumArtContact.builder()
                .type(dto.getType().getContact())
                .content(dto.getContent())
                .albumArtInfo(albumArtInfo)
                .build();
    }
}
