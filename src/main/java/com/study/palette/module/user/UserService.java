package com.study.palette.module.user;


import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.dto.*;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
                        .build());

        return UserProfileDto.builder()
                .id(userRepository.save(newUser).getId().toString())
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
                .userProfileDto(UserProfileDto.builder()
                        .id(user.getId().toString())
                        .email(user.getEmail())
                        .build())
                .build();

        if (user.getRole() == Role.MUSICIAN) {
            // TODO 음악인 정보 조회
//            myInfoResponseDto.setMusicianProfileDto(musicianService.getMusicianByIdWithDto(id));
        }

        return myInfoResponseDto;
    }

    //아이디찾기 Name and Phone with DTO
    public UserFindEmailDto getUserByNameAndPhoneWithDto(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

        return UserFindEmailDto.builder()
                .name(user.getName())
                .phone(user.getPhone())
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


    //TODO 회원목록조회 ?


}