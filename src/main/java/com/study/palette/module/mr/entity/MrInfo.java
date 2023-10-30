package com.study.palette.module.mr.entity;

import com.study.palette.module.musician.entity.UserMusician;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MrInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 50)
    private String serviceName;

    private int musicLength;

    @Column(length = 20)
    private String mood;

    private int salesType;

    @Column(length = 20)
    private String genre;

    private int serviceStatus;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private UserMusician userMusician;

    @OneToMany(mappedBy = "mrInfo", fetch = FetchType.LAZY)
    private List<MrLicenseInfo> mrLicenseInfo = new ArrayList<>();

    @OneToMany(mappedBy = "mrInfo", fetch = FetchType.LAZY)
    private List<MrReview> mrReview = new ArrayList<>();
    @OneToMany(mappedBy = "mrInfo", fetch = FetchType.LAZY)
    private List<MrFile> mrFile = new ArrayList<>();

}
