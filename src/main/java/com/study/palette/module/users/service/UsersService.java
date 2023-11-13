package com.study.palette.module.users.service;


import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.user.dto.UserChangePasswordDto;
import com.study.palette.module.users.dto.MyInfoResponseDto;
import com.study.palette.module.users.dto.UserCreateRequestDto;
import com.study.palette.module.users.dto.UserEmailDto;
import com.study.palette.module.users.dto.UserFindPasswordDto;
import com.study.palette.module.users.dto.UserProfileDto;
import com.study.palette.module.users.dto.UserUpdateDto;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.exception.UserErrorCode;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  //    public final MusicianService musicianService; TODO 서비스 구현시 추가
  @Autowired
  public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  //유저생성
  @Transactional
  public UserProfileDto createUser(UserCreateRequestDto userCreateRequestDto) {
    Optional<Users> user = getUserByEmail(userCreateRequestDto.getEmail());

    if (user.isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 회원입니다.");
    }

    Users newUsers = usersRepository.save(
        Users.builder()
            .role(Role.MEMBER) // User 에서는 권한을 MEMBER 로 고정
            .email(userCreateRequestDto.getEmail())
            .password(passwordEncoder.encode(userCreateRequestDto.getPassword()))
            .name(userCreateRequestDto.getName())
            .phone(userCreateRequestDto.getPhone())
            .isAlarmAccept(userCreateRequestDto.isAlarmAccept())
            .loginFailCount(0)
            .isLocked(false)
            .build());

    return UserProfileDto.builder()
        .id(usersRepository.save(newUsers).getId().toString())
        .role(newUsers.getRole())
        .email(newUsers.getEmail())
        .name(newUsers.getName())
        .phone(newUsers.getPhone())
        .isAlarmAccept(newUsers.isAlarmAccept())
        .loginFailCount(newUsers.getLoginFailCount())
        .isLocked(newUsers.isLocked())
        .build();
  }

  //회원목록 조회
  public PaginationDto<UserProfileDto> getUserList(Pageable pageable) {
    Long count = usersRepository.count();
    if (count < 1) {
      throw new CustomException(UserErrorCode.USER_NOT_FOUND);
    }

    List<UserProfileDto> users = usersRepository.findAll(pageable).stream()
        .map(UserProfileDto::new).collect(Collectors.toList());
    PaginationDto<UserProfileDto> row = PaginationDto.of(new PagingDto(pageable, count), users);
    return row;
  }

  //회원조회 ID
  public Users getUserById(String id) {
    return usersRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
  }

  //회원조회 ID with DTO
  public MyInfoResponseDto getUserByIdWithDto(Users users) {
    return MyInfoResponseDto.builder()
        .id(users.getId().toString())
        .role(users.getRole())
        .profileImage(users.getProfileImage())
        .nickname(users.getNickname())
        .build();
  }

  //아이디찾기 Name and Phone with DTO
  public UserEmailDto getUserByNameAndPhoneWithDto(String name, String phone) {
    Users users = usersRepository.findByNameAndPhone(name, phone)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    return UserEmailDto.builder()
        .email(users.getEmail())
        .build();
  }

  //회원조회 email
  public Optional<Users> getUserByEmail(String email) {
    return usersRepository.findByEmail(email);
  }

  //회원정보 수정
  @Transactional
  public void updateUser(String id, UserUpdateDto userUpdateDto) {
    Users users = usersRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    PaletteUtils.myCopyProperties(userUpdateDto, users);

    usersRepository.save(users);
  }

  //삭제시간 생성 - soft
  @Transactional
  public void generateDeletedAt(String id) {
    Users users = usersRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    users.generateDeletedAt();

    usersRepository.save(users);
  }

  //회원 영구 삭제 - hard
  @Transactional
  public void deleteUser(String id) {
    usersRepository.deleteById(UUID.fromString(id));
  }

  //비밀번호 찾기
  @Transactional
  public UserFindPasswordDto findPassword(String email) {
    Users user = usersRepository.findByEmail(email)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    user.setPassword(passwordEncoder.encode(generatePassword()));
    usersRepository.save(user);

    //TODO 존재하는 이메일일시 임시 비밀번호 발송

    return new UserFindPasswordDto(user.getEmail());
  }

  //비밀번호 변경 = 현재비밀번호 입력하여 같은지 확인후 변경처리 한다
  @Transactional
  public void updatePassword(UserChangePasswordDto userChangePasswordDto, String id) {
    Users user = usersRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    if (!passwordEncoder.matches(userChangePasswordDto.getPassword(), user.getPassword())) {
      throw new CustomException(UserErrorCode.USER_PASSWORD_NOT_MATCH);
    }

    user.setPassword(passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
    usersRepository.save(user);
  }

  //임의에 비밀번호 생성
  public String generatePassword() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}