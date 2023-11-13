package com.study.palette.module.users.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.user.dto.UserChangePasswordDto;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.dto.FindUserQuery;
import com.study.palette.module.users.dto.MyInfoResponseDto;
import com.study.palette.module.users.dto.UserCreateRequestDto;
import com.study.palette.module.users.dto.UserEmailDto;
import com.study.palette.module.users.dto.UserFindEmailDto;
import com.study.palette.module.users.dto.UserFindPasswordDto;
import com.study.palette.module.users.dto.UserProfileDto;
import com.study.palette.module.users.dto.UserUpdateDto;
import com.study.palette.module.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "유저 API")
@Log4j2
public class UserController {

  public final UsersService userService;

  @Autowired
  public UserController(UsersService userService) {
    this.userService = userService;
  }

  /**
   * 회원 조회
   */
  @Operation(summary = "내 정보 조회", description = "내 정보를 조회 합니다. 요청한 유저 권한이 MUSICIAN 일 경우, 음악인 정보도 함께 조회됩니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyInfoResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("me")
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  public ResponseEntity<MyInfoResponseDto> getMyInfo(
      @Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
    return ResponseEntity.ok(myInfoResponseDto);
  }

  /**
   * 회원 조회 By Email
   * TODO 휴대폰 인증 로직 추가
   */
  @Operation(summary = "email 조회", description = "이름과 휴대전화 번호로 email을 조회 합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEmailDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("find-email")
  public UserEmailDto findUserByName(@Valid @RequestBody UserFindEmailDto userFindIdDto) {
    return userService.getUserByNameAndPhoneWithDto(userFindIdDto.getName(),
        userFindIdDto.getPhone());
  }

  /**
   * 비밀번호 찾기
   */
  @Operation(summary = "비밀번호 찾기", description = "email이 유효하면 해당 이메일로 임시 비밀번호를 발송합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "발송 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserFindPasswordDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("find-password")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public UserFindPasswordDto findPassword(
      @Valid @RequestBody UserFindPasswordDto userFindPasswordDto) {
    return userService.findPassword(userFindPasswordDto.getEmail());
  }


  /**
   * 회원 생성
   */
  @Operation(summary = "회원 생성", description = "회원 생성 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  UserProfileDto createUser(@Valid @RequestBody UserCreateRequestDto user) {
    return userService.createUser(user);
  }

  /**
   * 회원 목록 조회
   */
  @Operation(summary = "회원 목록 조회", description = "회원 목록 조회 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  PaginationDto<UserProfileDto> getUserList(@ParameterObject FindUserQuery query) {
    return userService.getUserList(query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  /**
   * 회원 정보 수정
   */
  @Operation(summary = "회원 수정", description = "회원 수정 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserUpdateDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  void updateUser(@RequestBody UserUpdateDto user,
      @Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
    userService.updateUser(myInfoResponseDto.getId(), user);
  }

  /**
   * 회원 탈퇴 soft
   */
  @Operation(summary = "회원 탈퇴 - soft", description = "회원 탈퇴 입니다. 회원 탈퇴 시, 회원 정보는 삭제되지 않고, 삭제된 시간이 기록됩니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "탈퇴 성공"),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("soft")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  void softDelete(@Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
    userService.generateDeletedAt(myInfoResponseDto.getId());
  }

  /**
   * 회원 영구 삭제 hard
   */
  @Operation(summary = "회원 영구 삭제 - hard", description = "회원 영구 삭제 입니다. 회원 영구 삭제 시, 회원 정보는 삭제됩니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "삭제 성공"),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("hard")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  void hardDelete(@Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
    userService.deleteUser(myInfoResponseDto.getId());
  }

  /**
   * 비밀번호 변경
   */
  @Operation(summary = "비밀번호 변경", description = "비밀번호 변경 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "변경 성공"),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("password")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  void changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto,
      @Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
    userService.updatePassword(userChangePasswordDto, myInfoResponseDto.getId());
  }
}
