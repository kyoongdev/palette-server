package com.study.palette.test.jpa.crud.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@ToString
@Builder
@Getter
@Entity
@Table(name = "MixMasteringInfo")
public class MixMasteringInfo {

    @Id
    private String id;

    @Column(name="serviceName")
    private String serviceName;
    @Column(name="artistId")
    private String artistId;

    @Column(name="serviceExplain")
    private String serviceExplain;

    @Column(name="editInfo")
    private String editInfo;

    @Column(name="serviceStatus")
    private boolean serviceStatus;

    @Column(name="cratedAt")
    private Date cratedAt;

    @Column(name="userId")
    private String userId;




    @Builder
    public MixMasteringInfo(String id, String serviceName, String artistId, String serviceExplain, String editInfo, boolean serviceStatus, Date cratedAt, String userId) {
        this.id = id;
        this.serviceName = serviceName;
        this.artistId = artistId;
        this.serviceExplain = serviceExplain;
        this.editInfo = editInfo;
        this.serviceStatus = serviceStatus;
        this.cratedAt = cratedAt;
        this.userId = userId;
    }
}
