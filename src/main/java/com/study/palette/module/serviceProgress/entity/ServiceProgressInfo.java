package com.study.palette.module.serviceProgress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.user.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ServiceProgressInfo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private int serviceType;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID serviceId; // service 구분을 위해 추가

    private int licenseType;

    private int price;

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    private LocalDateTime endDate;

    private int workProgress;

    private boolean isComplete;

    @Column(length = 200)
    private String completeComment;

    private boolean status;

    @Column(length = 200)
    private String refundComment;

    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "serviceProgressInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ServiceProgressFile> serviceProgressFile = new ArrayList<>();

}
