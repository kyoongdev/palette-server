package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto extends User {



    @Email
    private String email;

    private String password;


    private String name;

    private String phone;

    private boolean isAlarmAccept;

}
