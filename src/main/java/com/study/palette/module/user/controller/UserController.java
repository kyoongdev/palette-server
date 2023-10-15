package com.study.palette.module.user.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.user.annotation.GetUserInfo;
import com.study.palette.module.user.service.UserService;
import com.study.palette.module.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Tag(name = "User", description = "유저 API")
@Log4j2
public class UserController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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
    public ResponseEntity<MyInfoResponseDto> getMyInfo(@Parameter(hidden = true) @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
    public UserEmailDto findUserByName(@RequestBody UserFindEmailDto userFindIdDto) {
        return userService.getUserByNameAndPhoneWithDto(userFindIdDto.getName(), userFindIdDto.getPhone());
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
    public UserFindPasswordDto findPassword(@RequestBody UserFindPasswordDto userFindPasswordDto) {
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
    UserProfileDto createUser(@RequestBody UserCreateRequestDto user) {
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
//    @PreAuthorize("hasRole('ROLE_ADMIN')") TODO ADMIN 권한만 가능하게
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
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
    void updateUser(@PathVariable("id") String id, @RequestBody UserUpdateDto user) {
        userService.updateUser(id, user);
    }

    /**
     * 회원 탈퇴 soft
     */
    @Operation(summary = "회원 탈퇴 - soft", description = "회원 탈퇴 입니다. 회원 탈퇴 시, 회원 정보는 삭제되지 않고, 삭제된 시간이 기록됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "탈퇴 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
    void softDelete(@PathVariable("id") String id) {
        userService.generateDeletedAt(id);
    }

    /**
     * 회원 영구 삭제 hard
     */
    @Operation(summary = "회원 영구 삭제 - hard", description = "회원 영구 삭제 입니다. 회원 영구 삭제 시, 회원 정보는 삭제됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("{id}/hard")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void hardDelete(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

}
