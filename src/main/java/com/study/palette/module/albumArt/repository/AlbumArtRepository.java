package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.artist.entity.ArtistInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumArtRepository extends JpaRepository<AlbumArtInfo, String> {
    Page<AlbumArtInfo> findAllByAlbumArtFileIsThumbnail(int key, boolean isUse, Pageable pageable);
}
