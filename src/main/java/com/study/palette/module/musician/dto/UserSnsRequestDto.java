package com.study.palette.module.musician.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSnsRequestDto {

    private int type;

    private String address;

    private LocalDate createAt;


}