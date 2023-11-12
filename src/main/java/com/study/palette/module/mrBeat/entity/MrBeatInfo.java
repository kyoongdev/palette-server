package com.study.palette.module.mrBeat.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MrBeatInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(length = 50)
    private String serviceName;

    private int mood;

    private int salesType;

    private int genre;

    private boolean serviceStatus;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MrBeatLicenseInfo> mrBeatLicenseInfo = new ArrayList<>();


    @OneToMany(mappedBy = "mrBeatInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MrBeatFile> mrBeatFileList = new ArrayList<>();

}
