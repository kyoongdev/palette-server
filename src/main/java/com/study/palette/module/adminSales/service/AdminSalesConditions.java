package com.study.palette.module.adminSales.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.dto.PageDto;
import com.study.palette.module.recording.entity.QRecordingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AdminSalesConditions extends PageDto {

  @JsonIgnore
  private int isRegistrationCompleted;

  public boolean isRegistrationCompleted() {
    if (isRegistrationCompleted == 1) {
      return true;
    } else if (isRegistrationCompleted == 0) {
      return false;
    }
    throw new RuntimeException("판매 등록 여부는 0과 1만 가능합니다");
  }

  public AdminSalesConditions(int isRegistrationCompleted) {
    this.isRegistrationCompleted = isRegistrationCompleted;
    System.out.println("AdminSalesConditions 생성자 호출");
  }

  //Setter
  public void setIsRegistrationCompleted(int isRegistrationCompleted) {
    this.isRegistrationCompleted = isRegistrationCompleted;
  }
}