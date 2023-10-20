package com.study.palette.module.albumArt.service;

import com.study.palette.common.constants.ServiceType;
import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.serviceProgress.entity.ServiceProgressInfo;
import com.study.palette.module.user.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumArtSpecification {
//id
//serviceName
//salesType
//userName
//fileUrl
//price
    public static Specification<AlbumArtInfo> filterByPOPULAR() {
        return (root, query, criteriaBuilder) -> {
            Join<AlbumArtInfo, ServiceProgressInfo> joinProgressInfo = root.join("serviceProgressInfos", JoinType.LEFT);
            Join<AlbumArtInfo, User> joinUser = root.join("user", JoinType.LEFT);
            Join<AlbumArtInfo, AlbumArtFile> joinFile = root.join("file", JoinType.LEFT);

            List<Expression<?>> expressions = new ArrayList<>();
            expressions.add(root.get("id"));
            expressions.add(root.get("serviceName"));
            expressions.add(root.get("salesType"));
            expressions.add(joinUser.get("userName"));
            expressions.add(joinFile.get("fileUrl"));
            expressions.add(joinProgressInfo.get("price"));
            expressions.add(criteriaBuilder.sum(joinProgressInfo.get("price")));
//            expressions.add(criteriaBuilder.count(joinProgressInfo.get("price")));

            query.groupBy(expressions);
            query.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(joinProgressInfo.get("price"))));

            return criteriaBuilder.and(
                    criteriaBuilder.equal(joinProgressInfo.get("serviceType"), ServiceType.ALBUM_ART.getCode()),
                    criteriaBuilder.isTrue(joinProgressInfo.get("isComplete"))
            );
        };
    }

    public static Specification<AlbumArtInfo> filterByNEW() {
        return null;
    }


    public static Specification<AlbumArtInfo> filterByRECOMMEND() {
        return null;
    }

    public static Specification<AlbumArtInfo> filterBySCORE() {
        return null;
    }
}
