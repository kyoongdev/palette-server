package com.study.palette.module.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileUploadDto {
    String url;
    String originFileName;
    String uploadFileName;
    String uploadFileSize;
    String suffix;
}
