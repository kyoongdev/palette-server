package com.study.palette.module.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatFile is a Querydsl query type for ChatFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatFile extends EntityPathBase<ChatFile> {

    private static final long serialVersionUID = 1264087974L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatFile chatFile = new QChatFile("chatFile");

    public final QChatMessage chatMessage;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> filterMasterCode = createNumber("filterMasterCode", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath userId = createString("userId");

    public QChatFile(String variable) {
        this(ChatFile.class, forVariable(variable), INITS);
    }

    public QChatFile(Path<? extends ChatFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatFile(PathMetadata metadata, PathInits inits) {
        this(ChatFile.class, metadata, inits);
    }

    public QChatFile(Class<? extends ChatFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatMessage = inits.isInitialized("chatMessage") ? new QChatMessage(forProperty("chatMessage"), inits.get("chatMessage")) : null;
    }

}

