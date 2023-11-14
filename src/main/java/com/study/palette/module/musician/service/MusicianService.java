package com.study.palette.module.musician.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.UpdateMusicianDto;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.entity.UsersPosition;
import com.study.palette.module.musician.entity.UsersSns;
import com.study.palette.module.musician.repository.UsersMusicianRepository;
import com.study.palette.module.users.dto.MyInfoResponseDto;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MusicianService {

    private final UsersMusicianRepository usersMusicianRepository;

    private final UsersRepository usersRepository;

    @Autowired
    public MusicianService(UsersMusicianRepository usersMusicianRepository, UsersRepository usersRepository) {
        this.usersMusicianRepository = usersMusicianRepository;
        this.usersRepository = usersRepository;
    }

    /* 뮤지션 생성 */
    //TODO 이미 뮤지션 생성을 했을 때 예외처리
    @Transactional
    public MyInfoResponseDto createMuscian(CreateMusicianDto createMusicianDto, String id) {

        Users user = usersRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

        UsersMusician usersMusician = createMusicianDto.toEntity(user);

        List<UsersSns> usersSns = createMusicianDto.getSnsAddress().stream()
            .map(userSnsList -> UsersSns.from(userSnsList, usersMusician))
            .toList();

        List<UsersPosition> usersPosition = createMusicianDto.getPositionType().stream()
            .map(userPositionList -> UsersPosition.from(userPositionList, usersMusician))
            .toList();

        usersMusician.setUsersPosition(usersPosition);
        usersMusician.setUsersSns(usersSns);

        usersMusicianRepository.save(usersMusician);

//        MyInfoResponseDto myInfoResponseDto = MyInfoResponseDto.builder()
//            .users(user)
//            .build();

//        return myInfoResponseDto;
        return null; //TODO MyInfoResponseDto 를 유저 전용 dto로 변경함 뮤지션은 따로 dto를 만들어야함


    }

    @Transactional
    public ResponseWithIdDto updateMusician(String id, UpdateMusicianDto updateMusicianDto) {

        UsersMusician updateUsersMusician = usersMusicianRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 뮤지션 정보가 없습니다."));

        PaletteUtils.myCopyProperties(updateMusicianDto, updateUsersMusician);

        updateUsersMusician.getUsersSns().clear();
        updateUsersMusician.getUsersPosition().clear();

        updateMusicianDto.getSnsAddress().stream().forEach(userSnsList -> {
            updateUsersMusician.getUsersSns().add(UsersSns.from(userSnsList, updateUsersMusician));
        });

        updateMusicianDto.getPositionType().stream().forEach(userPositionList -> {
            updateUsersMusician.getUsersPosition().add(UsersPosition.from(userPositionList,
                updateUsersMusician));
        });

        usersMusicianRepository.save(updateUsersMusician);

        return ResponseWithIdDto.builder().id(updateUsersMusician.getId()).build();
    }

    @Transactional
    public void deleteMusician(String id) {
        usersMusicianRepository.deleteById(id);
    }
}
