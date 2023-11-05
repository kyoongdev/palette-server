package com.study.palette.module.musician.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.UpdateMusicianDto;
import com.study.palette.module.musician.entity.UserMusician;
import com.study.palette.module.musician.entity.UserPosition;
import com.study.palette.module.musician.entity.UserSns;
import com.study.palette.module.musician.repository.UserMusicianRepository;
import com.study.palette.module.user.dto.MyInfoResponseDto;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MusicianService {

    private final UserMusicianRepository userMusicianRepository;

    private final UserRepository userRepository;

    @Autowired
    public MusicianService(UserMusicianRepository userMusicianRepository, UserRepository userRepository) {
        this.userMusicianRepository = userMusicianRepository;
        this.userRepository = userRepository;
    }

    /* 뮤지션 생성 */
    //TODO 이미 뮤지션 생성을 했을 때 예외처리
    @Transactional
    public MyInfoResponseDto createMuscian(CreateMusicianDto createMusicianDto, String id) {

        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

        UserMusician userMusician = createMusicianDto.toEntity(user);

        List<UserSns> userSns = createMusicianDto.getSnsAddress().stream()
                .map(userSnsList -> UserSns.from(userSnsList, userMusician))
                .toList();

        List<UserPosition> userPosition = createMusicianDto.getPositionType().stream()
                .map(userPositionList -> UserPosition.from(userPositionList, userMusician))
                .toList();

        userMusician.setUserPosition(userPosition);
        userMusician.setUserSns(userSns);

        userMusicianRepository.save(userMusician);

        MyInfoResponseDto myInfoResponseDto = MyInfoResponseDto.builder()
                .user(user)
                .build();

        return myInfoResponseDto;

    }

    @Transactional
    public ResponseWithIdDto updateMusician(String id, UpdateMusicianDto updateMusicianDto) {

        UserMusician updateUserMusician = userMusicianRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 뮤지션 정보가 없습니다."));

        PaletteUtils.myCopyProperties(updateMusicianDto, updateUserMusician);

        updateUserMusician.getUserSns().clear();
        updateUserMusician.getUserPosition().clear();

        updateMusicianDto.getSnsAddress().stream().forEach(userSnsList -> {
            updateUserMusician.getUserSns().add(UserSns.from(userSnsList, updateUserMusician));
        });

        updateMusicianDto.getPositionType().stream().forEach(userPositionList -> {
            updateUserMusician.getUserPosition().add(UserPosition.from(userPositionList, updateUserMusician));
        });

        userMusicianRepository.save(updateUserMusician);

        return ResponseWithIdDto.builder().id(updateUserMusician.getId()).build();
    }

    @Transactional
    public void deleteMusician(String id) {
        userMusicianRepository.deleteById(id);
    }
}
