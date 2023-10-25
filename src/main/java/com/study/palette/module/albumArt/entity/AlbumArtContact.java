package com.study.palette.module.albumArt.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "albumArtInfoId")
    private AlbumArtInfo albumArtInfo;
}
