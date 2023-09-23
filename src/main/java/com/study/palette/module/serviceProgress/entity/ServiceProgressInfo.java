package com.study.palette.module.serviceProgress.entity;

import com.study.palette.module.user.entity.User;
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
public class ServiceProgressInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 50)
    private String serviceName;

    private int licenseType;

    private int price;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDate endDate;

    private int workProgress;

    private boolean isComplete;

    @Column(length = 200)
    private String completeComment;

    private boolean status;

    @Column(length = 200)
    private String refundComment;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "serviceProgressInfo", fetch = FetchType.LAZY)
    private List<ServiceProgressFile> ServiceProgressFile = new ArrayList<>();




}
