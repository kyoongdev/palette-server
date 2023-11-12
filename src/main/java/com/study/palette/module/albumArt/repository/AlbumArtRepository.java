package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumArtRepository extends JpaRepository<AlbumArtInfo, String>,
    AlbumArtCustomRepository {

  Optional<AlbumArtInfo> findById(UUID id);

  AlbumArtInfo findByServiceName(String serviceName);
}
