package com.study.palette.module.adminSales.repository;

import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.service.AdminSalesConditions;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AdminSalesCustomRepository {
  List<AdminSalesResponseDto> findAllByIsSellingAndCreatedAtDesc(AdminSalesConditions query, Pageable pageable);
}
