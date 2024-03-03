package com.study.palette.module.file.service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface AudioFileProcessor {

  int getDuration(MultipartFile multipartFile);

}
