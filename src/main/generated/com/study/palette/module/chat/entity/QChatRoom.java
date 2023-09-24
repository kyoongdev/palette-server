package com.study.palette.module.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 1264451333L;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final ListPath<ChatMessage, QChatMessage> chatMessage = this.<ChatMessage, QChatMessage>createList("chatMessage", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final ListPath<ChatRoomUser, QChatRoomUser> chatRoomUser = this.<ChatRoomUser, QChatRoomUser>createList("chatRoomUser", ChatRoomUser.class, QChatRoomUser.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final StringPath userId = createString("userId");

    public QChatRoom(String variable) {
        super(ChatRoom.class, forVariable(variable));
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoom(PathMetadata metadata) {
        super(ChatRoom.class, metadata);
    }

}

