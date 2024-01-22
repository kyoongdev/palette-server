package com.study.palette.common.repository;

import com.study.palette.common.entity.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends JpaRepository<Holidays, String> {
  long countByHolidayIsNotNullAndSolarBetween(String solar, String solar2);
}
