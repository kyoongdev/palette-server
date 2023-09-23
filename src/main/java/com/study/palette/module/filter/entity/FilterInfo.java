package com.study.palette.module.filter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FilterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(length = 40)
    private String codeName;

    private boolean isUse;

    private int filterMasterCode;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "\"key\"")
    private FilterMaster key;
}
