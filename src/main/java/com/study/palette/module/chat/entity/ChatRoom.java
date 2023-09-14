package com.study.palette.module.chat.entity;

import com.study.palette.module.user.entity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    private LocalDate createdAt;

    @Column(length = 24)
    private String userId;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessage = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomUser> chatRoomUser = new ArrayList<>();

}
