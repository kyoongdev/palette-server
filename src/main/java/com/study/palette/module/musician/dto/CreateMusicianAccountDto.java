package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.musician.MusicianAccountType;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.entity.UsersMusicianAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMusicianAccountDto {

  @Schema(description = "종류", example = "1 : 국민은행, 2 : 신한은행, 3 : 우리은행, 4 : 카카오뱅크, 5 : 농협은행, 6 : 외환은행, 7 : SC은행, 8 : 시티은행, 9 : 산업은행, 10 : 하나은행, 11 : 토스뱅크, 12 : 기타")
  private int code;

  @Schema(description = "계좌번호")
  private String account;

  @Schema(description = "예금주")
  private String accountHolder;

  public UsersMusicianAccount toEntity(UsersMusician usersMusician) {
    return UsersMusicianAccount.builder()
        .code(MusicianAccountType.findMusicianAccount(this.getCode()))
        .account(this.getAccount())
        .accountHolder(this.getAccountHolder())
        .createdAt(LocalDateTime.now())
        .usersMusician(usersMusician)
        .users(usersMusician.getUsers())
        .build();
  }

}
