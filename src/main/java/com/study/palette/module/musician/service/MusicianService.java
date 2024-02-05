package com.study.palette.module.musician.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.UpdateMusicianDto;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.repository.UsersMusicianRepository;
import com.study.palette.module.users.dto.MyInfoResponseDto;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MusicianService {

  private final UsersMusicianRepository usersMusicianRepository;

  private final UsersRepository usersRepository;

  @Autowired
  public MusicianService(UsersMusicianRepository usersMusicianRepository,
      UsersRepository usersRepository) {
    this.usersMusicianRepository = usersMusicianRepository;
    this.usersRepository = usersRepository;
  }

  /* 뮤지션 생성 */
  //TODO 이미 뮤지션 생성을 했을 때 예외처리
  @Transactional
  public ResponseWithIdDto createMuscian(CreateMusicianDto createMusicianDto, Users users) {

    Optional<UsersMusician> isExist = usersMusicianRepository.findByUsers(users);

//    if (isExist.isPresent()) {
//      throw new MusicianException(MusicianErrorCode.MUSICIAN_EXIST);
//    }

    UsersMusician usersMusician = createMusicianDto.toEntity(users);

    usersMusicianRepository.save(usersMusician);

    return ResponseWithIdDto.builder().id(usersMusician.getId().toString()).build();

  }

//  @Transactional
//  public ResponseWithIdDto updateMusician(String id, UpdateMusicianDto updateMusicianDto) {
//
//    UsersMusician updateUsersMusician = usersMusicianRepository.findById(id)
//        .orElseThrow(() -> new IllegalArgumentException("해당 뮤지션 정보가 없습니다."));
//
//    PaletteUtils.myCopyProperties(updateMusicianDto, updateUsersMusician);
//
//    updateUsersMusician.getUsersSns().clear();
//    updateUsersMusician.getUsersPosition().clear();
//
//    updateMusicianDto.getSnsAddress().stream().forEach(userSnsList -> {
//      updateUsersMusician.getUsersSns().add(UsersSns.from(userSnsList, updateUsersMusician));
//    });
//
//    updateMusicianDto.getPositionType().stream().forEach(userPositionList -> {
//      updateUsersMusician.getUsersPosition().add(UsersPosition.from(userPositionList,
//          updateUsersMusician));
//    });
//
//    usersMusicianRepository.save(updateUsersMusician);
//
//    return ResponseWithIdDto.builder().id(updateUsersMusician.getId()).build();
//  }
//
//  @Transactional
//  public void deleteMusician(String id) {
//    usersMusicianRepository.deleteById(id);
//  }
}
