package com.study.palette.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Holidays {
  // 순번, 음력, 양력, 윤년여부, 공휴일
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int seq;

  @Column(nullable = false, length = 10)
  private String lunar;

  @Column(nullable = false, length = 10)
  private String solar;

  @Column(nullable = false)
  private boolean isLeapYear;

  @Column(length = 20)
  private String holiday;
}
