package com.study.palette.common.enums;

public enum LicenseType {

  BASIC(1),
  PREMIUM(2),
  UNLIMITED(1);

  private int licenseType;

  LicenseType(int licenseType) {
    this.licenseType = licenseType;
  }

  public static LicenseType findContact(int licenseType) {
    for (LicenseType type : LicenseType.values()) {
      if (type.licenseType == licenseType) {
        return type;
      }
    }
    throw new RuntimeException("licenseType은 1 ~ 3 까지만 가능합니다.");
  }

}
