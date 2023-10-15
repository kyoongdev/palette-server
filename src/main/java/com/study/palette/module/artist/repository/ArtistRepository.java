package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.entity.ArtistInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistRepository extends JpaRepository<ArtistInfo, String> {
//    Optional<ArtistInfo> findByA(String name);

    Long countByFilterInfo_Code(int fiterCode);

    List<ArtistInfo> findAllByFilterInfo_Code(int fiterCode, Pageable pageable);


}
