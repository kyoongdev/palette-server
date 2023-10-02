package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.entity.ArtistInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistRepository extends JpaRepository<ArtistInfo, String> {
    Page<ArtistInfo> findAllByFilterInfo_CodeAndArtistFileIsThumbnail(int key, boolean isUse, Pageable pageable);
}
