package com.study.palette.module.file.enums;

import com.study.palette.module.file.service.AudioFileProcessor;
import com.study.palette.module.file.service.Mp3FileProcessor;
import com.study.palette.module.file.service.WavFileProcessor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AudioFileType {

  MP3("mp3", new Mp3FileProcessor()),
  WAV("wav", new WavFileProcessor());

  private String extension;
  private AudioFileProcessor processor;

  public AudioFileProcessor getProcessor() {
    return processor;
  }

  public static AudioFileType findFileExtension(String extension) {
    for (AudioFileType audioFileType : AudioFileType.values()) {
      if (audioFileType.getExtension().equals(extension)) {
        return audioFileType;
      }
    }
    return null;
  }


}
