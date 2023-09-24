package com.study.palette.config.swagger;

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
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi tempOpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("test group1 v1")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi testOpenApi() {
        String[] paths = {"/test/**"};
        String[] excludes = {"/temp/**"};

        return GroupedOpenApi.builder()
                .group("test group2 v1")
                .pathsToMatch(paths)
                .packagesToExclude(excludes)
                .build();
    }
}