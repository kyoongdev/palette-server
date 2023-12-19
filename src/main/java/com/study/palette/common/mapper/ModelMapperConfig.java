package com.study.palette.common.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  /* entity <-> dto 간 데이터 변한 */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
