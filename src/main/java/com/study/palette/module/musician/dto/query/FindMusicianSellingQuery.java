package com.study.palette.module.musician.dto.query;

import com.study.palette.common.dto.PageDto;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindMusicianSellingQuery extends PageDto {

  @Schema(description = "usersId", defaultValue = "")
  private String id;

  @Schema(description = "현재 서비스정보 id", defaultValue = "")
  private String excludeTargetId;

}
