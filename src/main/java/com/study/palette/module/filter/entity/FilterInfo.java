package com.study.palette.module.filter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FilterInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private int code;

    @Column(length = 40)
    private String codeName;

    private boolean isUse;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "filterMasterId")
    private FilterMaster filterMaster;

    @Builder
    public FilterInfo(int code, String codeName, boolean isUse, LocalDate createdAt, String userId, FilterMaster filterMaster) {
        this.code = code;
        this.codeName = codeName;
        this.isUse = isUse;
        this.createdAt = createdAt;
        this.userId = userId;
        this.filterMaster = filterMaster;
    }
}
