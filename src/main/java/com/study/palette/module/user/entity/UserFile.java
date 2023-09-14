package com.study.palette.module.user.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class UserFile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 256)
    private String originFileName;

    @Column(length = 256)
    private String uploadFileName;

    private int uploadFileSize;

    @Column(length = 256)
    private String uploadFilePath;

    private int filterMasterCode;

    @Column(length = 4)
    private String suffix;

    private boolean isUse;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
