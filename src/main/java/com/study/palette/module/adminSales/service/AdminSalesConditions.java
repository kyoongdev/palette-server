package com.study.palette.module.adminSales.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.dto.PageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AdminSalesConditions extends PageDto {

  @JsonIgnore
  private Boolean isRegistrationCompleted;

  public AdminSalesConditions(Boolean isRegistrationCompleted) {
    this.isRegistrationCompleted = isRegistrationCompleted;
    System.out.println("AdminSalesConditions 생성자 호출");
  }

  //Setter
  public void setIsRegistrationCompleted(Boolean isRegistrationCompleted) {
    this.isRegistrationCompleted = isRegistrationCompleted;
  }
}