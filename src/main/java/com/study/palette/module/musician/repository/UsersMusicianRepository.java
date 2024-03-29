package com.study.palette.module.musician.repository;

import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.users.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMusicianRepository extends JpaRepository<UsersMusician, String> {

  Optional<UsersMusician> findByUsers(Users users);


  Long countByUsers(Users users);
}
