package com.study.palette.module.user;

import com.study.palette.module.user.dto.UserCreateRequestDto;
import com.study.palette.module.user.dto.UserFindEmailDto;
import com.study.palette.module.user.dto.UserProfileDto;
import com.study.palette.module.user.dto.UserUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원 조회
     */
    @Operation(summary = "회원 조회 By ID", description = "ID 로 회원조회 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("{id}")
    public UserProfileDto findUserById(@PathVariable("id") String id) {
        return userService.getUserByIdWithDto(id);
    }

    /**
     * 회원 조회 By Email
     */
    @Operation(summary = "email 조회", description = "이름과 휴대전화 번호로 email을 조회 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserFindEmailDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("find-email")
    public UserFindEmailDto findUserByName(@RequestBody UserFindEmailDto userFindIdDto) {
        return userService.getUserByNameAndPhoneWithDto(userFindIdDto.getName(), userFindIdDto.getPhone());
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
     * 회원 정보 수정
     */
    @Operation(summary = "회원 수정", description = "회원 수정 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserUpdateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    void hardDelete(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

}
