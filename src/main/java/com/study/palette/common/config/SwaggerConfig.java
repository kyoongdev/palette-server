package com.study.palette.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
        info = @Info(title = "Palette API 명세서",
                description = "palette study api 명세서 입니다",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
@EnableWebMvc

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/temp/**"};

        return GroupedOpenApi.builder()
                .group("test group v1")
                .pathsToMatch(paths)
                .build();
    }
}