package com.study.palette.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "health 체크", description = "health 체크")
@RestController
@RequestMapping("/health")
public class HealthCheckController {

  @GetMapping("")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok().body("OK");
  }

}
