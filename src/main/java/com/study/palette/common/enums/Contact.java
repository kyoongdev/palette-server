package com.study.palette.common.enums;

public enum Contact {
  PHONE(1),
  EMAIL(2),
  KAKAO(3),
  INSTAGRAM(4),
  ETC(5);

  private int type;

  Contact(int type) {
    this.type = type;
  }

  public static Contact findContact(int type) {
    for (Contact contact : Contact.values()) {
      if (contact.type == type) {
        return contact;
      }
    }
    throw new RuntimeException("type은 1 ~ 5 까지만 가능합니다.");
  }


}
