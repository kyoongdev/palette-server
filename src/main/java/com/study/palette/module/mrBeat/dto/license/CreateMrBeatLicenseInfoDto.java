package com.study.palette.module.mrBeat.dto.license;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMrBeatLicenseInfoDto {

  private int licenseType;

  private int price;

}
