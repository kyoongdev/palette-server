package com.study.palette.module.albumArt.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.common.enums.albumArt.AlbumArtServiceType;
import com.study.palette.module.albumArt.dto.info.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.entity.*;
import com.study.palette.module.serviceProgress.entity.QServiceProgressInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AlbumArtRepositoryImpl implements AlbumArtCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public AlbumArtRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<AlbumArtResponseDto> findAll(FindAlbumArtQuery query, Pageable pageable) {
        QAlbumArtInfo albumArtInfo = QAlbumArtInfo.albumArtInfo;
        QAlbumArtFile albumArtFile = QAlbumArtFile.albumArtFile;
        QAlbumArtLicenseInfo albumArtLicenseInfo = QAlbumArtLicenseInfo.albumArtLicenseInfo;
        QServiceProgressInfo serviceProgressInfo = QServiceProgressInfo.serviceProgressInfo;

        List<AlbumArtResponseDto> result = queryFactory
                .select(Projections.constructor(AlbumArtResponseDto.class,
                        albumArtInfo.id,
                        albumArtInfo.serviceName,
                        albumArtInfo.salesType,
                        albumArtInfo.user.name.as("userName"),
                        albumArtFile.upoladFilePath.as("fileUrl"),
                        albumArtLicenseInfo.price.as("price"),
                        serviceProgressInfo.price.sum().as("totalPrice")))
                .from(albumArtInfo)
                .leftJoin(serviceProgressInfo)
                .on(albumArtInfo.id.eq(serviceProgressInfo.serviceId)
                        .and(serviceProgressInfo.isComplete.isTrue()
                                .and(serviceProgressInfo.serviceType.eq(AlbumArtServiceType.ALBUM_ART))))
                .leftJoin(albumArtFile)
                .on(albumArtInfo.id.eq(albumArtFile.albumArt.id)
                        .and(albumArtFile.isThumbnail.isTrue()))
                .leftJoin(albumArtLicenseInfo)
                .on(albumArtInfo.id.eq(albumArtLicenseInfo.albumArtInfo.id)
                        .and(albumArtLicenseInfo.licenseType.eq(10)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(albumArtInfo.id,
                        albumArtInfo.serviceName,
                        albumArtInfo.salesType,
                        albumArtInfo.user.name,
                        albumArtFile.upoladFilePath,
                        albumArtLicenseInfo.price)
                .orderBy(query.getSort())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
