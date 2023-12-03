package com.study.palette.module.adminService.repository;

import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminServiceCustomRepository {
  List<ServiceResponseDto> findAllByServiceStatusAndCreatedAtDesc(FindAdminServiceQuery query, Pageable pageable);
}
