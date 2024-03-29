package com.study.palette.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "Palette API 명세서",
        description = "palette study api 명세서 입니다",
        version = "v1"))

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

  @Value("${springdoc.servers.url}")
  private String host;

  @Value("${springdoc.servers.description}")
  private String description;

  @Bean
  public OpenAPI openApi() {
    SecurityScheme securityScheme = new SecurityScheme().type(Type.HTTP).scheme("bearer")
        .bearerFormat("JWT").in(SecurityScheme.In.HEADER).name("Authorization");

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");


    return new OpenAPI().addServersItem(new Server().url(host).description(description)).components(
            new Components().addSecuritySchemes("bearerAuth", securityScheme))
        .security(Arrays.asList(securityRequirement));

  }

}