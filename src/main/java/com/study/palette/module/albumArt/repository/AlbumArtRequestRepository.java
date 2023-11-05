package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtRequest;
import com.study.palette.module.user.entity.User;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlbumArtRequestRepository extends JpaRepository<AlbumArtRequest, String> {

  @Query("select a from AlbumArtRequest a where a.user = :user and a.albumArtInfo = :albumArtInfo and a.createAt = :now")
  Optional<AlbumArtRequest> findByAlbumArtInfoAndUserAndCreatedAt(User user,
      AlbumArtInfo albumArtInfo, LocalDate now);
}
