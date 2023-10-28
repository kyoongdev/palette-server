package com.study.palette.module.user.service;


import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.dto.MyInfoResponseDto;
import com.study.palette.module.user.dto.UserCreateRequestDto;
import com.study.palette.module.user.dto.UserEmailDto;
import com.study.palette.module.user.dto.UserProfileDto;
import com.study.palette.module.user.dto.UserUpdateDto;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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

  //회원조회 ID
  public Optional<User> getUserById(String id) {
    return userRepository.findById(UUID.fromString(id));
  }

  //회원조회 ID with DTO
  public MyInfoResponseDto getUserByIdWithDto(String id) {
    User user = userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

    MyInfoResponseDto myInfoResponseDto = MyInfoResponseDto.builder()
        .user(user)
        .build();

    if (user.getRole() == Role.MUSICIAN) {
      // TODO 음악인 정보 조회
//            myInfoResponseDto.setMusician(musicianService.getMusicianByIdWithDto(id));
    }

    return myInfoResponseDto;
  }

  //아이디찾기 Name and Phone with DTO
  public UserEmailDto getUserByNameAndPhoneWithDto(String name, String phone) {
    User user = userRepository.findByNameAndPhone(name, phone)
        .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

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
    User user = getUserById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

    PaletteUtils.myCopyProperties(userUpdateDto, user);

    userRepository.save(user);
  }

  //삭제시간 생성 - soft
  @Transactional
  public void generateDeletedAt(String id) {
    User user = getUserById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

    user.generateDeletedAt();

    userRepository.save(user);
  }

  //회원 영구 삭제 - hard
  @Transactional
  public void deleteUser(String id) {
    userRepository.deleteById(UUID.fromString(id));
  }


}