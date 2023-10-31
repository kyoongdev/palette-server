package com.study.palette.module.file.controller;

import com.study.palette.module.file.annotation.FileType;
import com.study.palette.module.file.dto.FileDeleteDto;
import com.study.palette.module.file.dto.FileUploadDto;
import com.study.palette.module.file.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/files")
@Validated
@Tag(name = "File", description = "파일 API")
public class FileUploadController {

  private FileUploadService fileUploadService;

  @Autowired
  public FileUploadController(FileUploadService fileUploadService) {
    this.fileUploadService = fileUploadService;
  }

  /**
   * 이미지 업로드
   */
  @PostMapping(path = "/image", consumes = "multipart/form-data")
  @Operation(summary = "이미지 업로드", description = "이미지를 업로드 합니다.")
  public ResponseEntity<FileUploadDto> imageUpload(
      @RequestBody @FileType("image") MultipartFile file) throws IOException {
    return ResponseEntity.ok(fileUploadService.saveFile(file));
  }

  /**
   * 파일 업로드
   */
  @PostMapping(path = "/file", consumes = "multipart/form-data")
  @Operation(summary = "파일 업로드", description = "파일을 업로드 합니다.")
  public ResponseEntity<FileUploadDto> fileUpload(
      @RequestBody @FileType(value = "image", isAllow = false) MultipartFile file)
      throws IOException {
    return ResponseEntity.ok(fileUploadService.saveFile(file));
  }

  /**
   * 이미지 삭제
   */
  @PostMapping(path = "/image/delete")
  @Operation(summary = "이미지 삭제", description = "이미지를 삭제 합니다.")
  public void deleteImage(@RequestBody FileDeleteDto body) {
    fileUploadService.deleteFile(body);
  }

  /**
   * 파일 삭제
   */
  @PostMapping(path = "/file/delete")
  @Operation(summary = "파일 삭제", description = "파일을 삭제 합니다.")
  public void deleteFile(@RequestBody FileDeleteDto body) {
    fileUploadService.deleteFile(body);
  }
}