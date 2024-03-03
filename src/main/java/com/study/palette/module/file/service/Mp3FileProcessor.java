package com.study.palette.module.file.service;

import com.study.palette.module.file.exeption.AudioFileErrorCode;
import com.study.palette.module.file.exeption.AudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import org.springframework.web.multipart.MultipartFile;

public class Mp3FileProcessor implements AudioFileProcessor{

  @Override
  public int getDuration(MultipartFile multipartFile) {

    int duration = 0;
    Path tempFile = null;
    InputStream inputStream = null;

    try {
      inputStream = multipartFile.getInputStream();
      tempFile = Files.createTempFile("temp", ".mp3");
      Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

      File file = tempFile.toFile();

      // mp3spi를 사용하여 재생시간 추출
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);

      Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
      String key = "duration";
      Long microseconds = (Long) properties.get(key);
      duration = (int) (microseconds / 1000) / 1000; // 밀리초 단위로 변환

      System.out.println("재생시간: " + duration + "ms");
      System.out.println("재생시간: " + duration / 1000 + "s");

      return duration;
    } catch (Exception e) {
      throw new AudioFileException(AudioFileErrorCode.AUDIO_FILE_NOT_CONVERT);
    } finally {
      // 임시 파일 삭제
      try {
        Files.delete(tempFile);
        inputStream.close();
      } catch (IOException e) {
        throw new AudioFileException(AudioFileErrorCode.AUDIO_FILE_NOT_DELETE_TEMP);
      }
    }

  }
}
