package com.study.palette.module.adminSales.repository;

import com.study.palette.module.adminSales.dto.FindAdminSalesQuery;
import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AdminSalesCustomRepository {
  List<AdminSalesResponseDto> findAllByServiceStatusAndCreatedAtDesc(FindAdminSalesQuery query, Pageable pageable);
}
