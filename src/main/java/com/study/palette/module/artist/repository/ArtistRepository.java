package com.study.palette.module.artist.repository;

import com.study.palette.module.artist.entity.ArtistInfo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistRepository extends JpaRepository<ArtistInfo, String>, ArtistCustomRepository {

  Long countBySalesType(int fiterCode);
  long countByServiceStatus(boolean registrationCompleted);
  List<ArtistInfo> findAllBySalesType(int fiterCode, Pageable pageable);


}
