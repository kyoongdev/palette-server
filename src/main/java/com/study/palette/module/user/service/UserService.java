package com.study.palette.module.user.service;


import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.user.dto.MyInfoResponseDto;
import com.study.palette.module.user.dto.UserChangePasswordDto;
import com.study.palette.module.user.dto.UserCreateRequestDto;
import com.study.palette.module.user.dto.UserEmailDto;
import com.study.palette.module.user.dto.UserFindPasswordDto;
import com.study.palette.module.user.dto.UserProfileDto;
import com.study.palette.module.user.dto.UserUpdateDto;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.exception.UserErrorCode;
import com.study.palette.module.user.repository.UserRepository;
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
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  //    public final MusicianService musicianService; TODO 서비스 구현시 추가
  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  //유저생성
  @Transactional
  public UserProfileDto createUser(UserCreateRequestDto userCreateRequestDto) {
    Optional<User> user = getUserByEmail(userCreateRequestDto.getEmail());

    if (user.isPresent()) {
      throw new IllegalArgumentException("이미 존재하는 회원입니다.");
    }

    User newUser = userRepository.save(
        User.builder()
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
        .id(userRepository.save(newUser).getId().toString())
        .role(newUser.getRole())
        .email(newUser.getEmail())
        .name(newUser.getName())
        .phone(newUser.getPhone())
        .isAlarmAccept(newUser.isAlarmAccept())
        .loginFailCount(newUser.getLoginFailCount())
        .isLocked(newUser.isLocked())
        .build();
  }

  //회원목록 조회
  public PaginationDto<UserProfileDto> getUserList(Pageable pageable) {
    Long count = userRepository.count();
    if (count < 1) {
      throw new CustomException(UserErrorCode.USER_NOT_FOUND);
    }

    List<UserProfileDto> users = userRepository.findAll(pageable).stream()
        .map(UserProfileDto::new).collect(Collectors.toList());
    PaginationDto<UserProfileDto> row = PaginationDto.of(new PagingDto(pageable, count), users);
    return row;
  }

  //회원조회 ID
  public User getUserById(String id) {
    return userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
  }

  //회원조회 ID with DTO
  public MyInfoResponseDto getUserByIdWithDto(String id) {
    User user = userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    MyInfoResponseDto myInfoResponseDto = MyInfoResponseDto.builder()
        .id(user.getId().toString())
        .role(user.getRole())
        .profileImage(user.getProfileImage())
        .nickname(user.getNickname())
        .build();

    return myInfoResponseDto;
  }

  //아이디찾기 Name and Phone with DTO
  public UserEmailDto getUserByNameAndPhoneWithDto(String name, String phone) {
    User user = userRepository.findByNameAndPhone(name, phone)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    return UserEmailDto.builder()
        .email(user.getEmail())
        .build();
  }

  //회원조회 email
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  //회원정보 수정
  @Transactional
  public void updateUser(String id, UserUpdateDto userUpdateDto) {
    User user = userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    PaletteUtils.myCopyProperties(userUpdateDto, user);

    userRepository.save(user);
  }

  //삭제시간 생성 - soft
  @Transactional
  public void generateDeletedAt(String id) {
    User user = userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    user.generateDeletedAt();

    userRepository.save(user);
  }

  //회원 영구 삭제 - hard
  @Transactional
  public void deleteUser(String id) {
    userRepository.deleteById(UUID.fromString(id));
  }

  //비밀번호 찾기
  @Transactional
  public UserFindPasswordDto findPassword(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    user.setPassword(passwordEncoder.encode(generatePassword()));
    userRepository.save(user);

    //TODO 존재하는 이메일일시 임시 비밀번호 발송

    return new UserFindPasswordDto(user.getEmail());
  }

  //비밀번호 변경 = 현재비밀번호 입력하여 같은지 확인후 변경처리 한다
  @Transactional
  public void updatePassword(UserChangePasswordDto userChangePasswordDto, String id) {
    User user = userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    if (!passwordEncoder.matches(userChangePasswordDto.getPassword(), user.getPassword())) {
      throw new CustomException(UserErrorCode.USER_PASSWORD_NOT_MATCH);
    }

    user.setPassword(passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
    userRepository.save(user);
  }

  //임의에 비밀번호 생성
  public String generatePassword() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}