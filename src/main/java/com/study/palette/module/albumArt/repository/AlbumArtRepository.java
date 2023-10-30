package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AlbumArtRepository extends JpaRepository<AlbumArtInfo, String>, AlbumArtCustomRepository {
    Optional<AlbumArtInfo> findById(UUID id);

    AlbumArtInfo findByServiceName(String serviceName);
}
