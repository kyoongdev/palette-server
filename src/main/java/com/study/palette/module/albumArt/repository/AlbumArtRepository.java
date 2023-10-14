package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AlbumArtRepository extends JpaRepository<AlbumArtInfo, String> {
//    Page<AlbumArtInfo> findByAlbumArtFile_isThumbnail(boolean isThumbnail, Pageable pageable);
    Page<AlbumArtInfo> findAll(Pageable pageable);

    Optional<AlbumArtInfo> findById(UUID id);

    AlbumArtInfo findByServiceName(String serviceName);

}
