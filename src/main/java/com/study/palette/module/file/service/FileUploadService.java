package com.study.palette.module.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.study.palette.module.file.dto.FileDeleteDto;
import com.study.palette.module.file.dto.FileUploadDto;
import com.study.palette.module.file.enums.AudioFileType;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${s3.naming-strategy.path}")
  private String pathPrefix;

  @Autowired
  public FileUploadService(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  //파일 업로드
  public FileUploadDto saveFile(MultipartFile multipartFile) throws IOException {
    String uploadFilename = getRandomString(
        Objects.requireNonNull(multipartFile.getOriginalFilename()));
    String type = multipartFile.getContentType();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(multipartFile.getSize());
    metadata.setContentType(multipartFile.getContentType());

    /* mp3, wav 파일 확장자*/
    String extension = multipartFile.getOriginalFilename();


    /* 오디오 파일 확장자별로 재생시간을 가져올 수 있는 로직이 달라 다형성으로 처리 */
    AudioFileType findAudioFileType = AudioFileType.findFileExtension(extension.substring(extension.lastIndexOf(".") + 1));

    if(findAudioFileType != null) {
      AudioFileProcessor findProcessor = findAudioFileType.getProcessor();

      int duration = findProcessor.getDuration(multipartFile);

      amazonS3.putObject(bucket, pathPrefix + "/" + uploadFilename, multipartFile.getInputStream(),
          metadata);

      return FileUploadDto.builder()
          .url(amazonS3.getUrl(bucket, uploadFilename).toString())
          .originFileName(multipartFile.getOriginalFilename())
          .uploadFileName(uploadFilename)
          .uploadFileSize(multipartFile.getContentType())
          .suffix(type.substring(type.lastIndexOf("/") + 1))
          .duration(duration)
          .build();
    }

    amazonS3.putObject(bucket, pathPrefix + "/" + uploadFilename, multipartFile.getInputStream(),
        metadata);

    return FileUploadDto.builder()
        .url(amazonS3.getUrl(bucket, uploadFilename).toString())
        .originFileName(multipartFile.getOriginalFilename())
        .uploadFileName(uploadFilename)
        .uploadFileSize(multipartFile.getContentType())
        .suffix(type.substring(type.lastIndexOf("/") + 1))
        .build();
  }

  //파일삭제
  public void deleteFile(FileDeleteDto body) {
    String[] splitValue = body.getUrl().split("/");
    amazonS3.deleteObject(bucket, pathPrefix + "/" + splitValue[splitValue.length - 1]);
  }

  private String getRandomString(String originalFilename) {
    int position = originalFilename.lastIndexOf(".");
    String ext = originalFilename.substring(position + 1);
    return UUID.randomUUID().toString() + "." + ext;
  }
}
