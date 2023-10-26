package com.study.palette.module.recording.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecordingFile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 24)
    private String id;

    @Column(length = 256)
    private String originFileName;

    @Column(length = 256)
    private String uploadFileName;

    private int uploadFileSize;

    @Column(length = 256)
    private String upoladFilePath;

    private int fileMasterCode;

    @ManyToOne
    @JoinColumn(name = "recordingId")
    private RecordingInfo recording;

    @Column(length = 4)
    private String suffix;

    private boolean isUse;

    private boolean isThumbnail;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "recordingInfoId")
    private RecordingInfo recordingInfo;

    public static RecordingFile from(MultipartFile recordingFiles, RecordingInfo recordingInfo) {
        return new RecordingFile(recordingFiles, recordingInfo);
    }

    public RecordingFile(MultipartFile recordingFile, RecordingInfo recordingInfo) {
        this.originFileName = recordingFile.getOriginalFilename();
        this.uploadFileName = PaletteUtils.generateUniqueName(recordingFile.getOriginalFilename());
        this.uploadFileSize = (int) recordingFile.getSize();
        this.upoladFilePath = "/upload"; //TODO 경로수정
        this.fileMasterCode = 1; //TODO 코드수정
        this.suffix = recordingFile.getContentType();
        this.isUse = true;
        this.isThumbnail = false;
        this.createdAt = LocalDate.now();

        if (recordingInfo != null) {
            setRecording(recordingInfo);
        }
    }

    public void setRecording(RecordingInfo recordingInfo) {
        if (this.recordingInfo != null) {
            this.recordingInfo.getRecordingFile().remove(this);
        }

        this.recordingInfo = recordingInfo;

        recordingInfo.getRecordingFile().add(this);
    }
}
