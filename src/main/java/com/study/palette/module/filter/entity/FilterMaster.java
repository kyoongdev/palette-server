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
public class FilterMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"key\"")
    private int key;

    @Column(length = 40)
    private String codeName;

    private boolean isUse;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

}
