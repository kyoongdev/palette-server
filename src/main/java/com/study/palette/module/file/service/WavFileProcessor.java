package com.study.palette.module.file.service;

import com.study.palette.module.file.exeption.AudioFileErrorCode;
import com.study.palette.module.file.exeption.AudioFileException;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import org.springframework.web.multipart.MultipartFile;

public class WavFileProcessor implements AudioFileProcessor{

  @Override
  public int getDuration(MultipartFile multipartFile) {

    int duration = 0;
    Path tempFile = null;
    InputStream inputStream = null;

    try {
      // 임시 파일 생성
      inputStream = multipartFile.getInputStream();
      tempFile = Files.createTempFile("temp", ".wav");
      Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

      File file = tempFile.toFile();

      // wav 파일은 자바에서 기본적으로 제공하는 AudioSystem을 사용하여 재생시간 추출
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      long frames = fileFormat.getFrameLength();
      AudioFormat format = fileFormat.getFormat();
      float frameRate = format.getFrameRate();
      duration = (int) ((frames / frameRate) * 1000) / 1000;  // 밀리초 단위로 변환


      // 임시 파일 삭제
      return duration;
    } catch (Exception e) {
      throw new AudioFileException(AudioFileErrorCode.AUDIO_FILE_NOT_CONVERT);
    } finally {
      // 임시 파일 삭제
      try {
         Files.delete(tempFile);
          inputStream.close();
      } catch (Exception e) {
        throw new AudioFileException(AudioFileErrorCode.AUDIO_FILE_NOT_DELETE_TEMP);
      }
    }

  }
}
