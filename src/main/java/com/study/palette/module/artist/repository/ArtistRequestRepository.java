package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistRequest;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRequestRepository extends JpaRepository<ArtistRequest, String> {

  @Query("select a from ArtistRequest a where a.users = :users and a.artistInfo = :artistInfo and a.createdAt = :now")
  Optional<ArtistRequest> findByArtistInfoAndUserAndCreatedAt(Users users,
      ArtistInfo artistInfo, LocalDateTime now);
}
