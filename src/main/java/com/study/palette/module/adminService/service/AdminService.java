package com.study.palette.module.adminService.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


  public PaginationDto<ServiceResponseDto> getServices(FindAdminServiceQuery query, Pageable pageable) {
    return null;
  }
}
