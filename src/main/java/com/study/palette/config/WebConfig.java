package com.study.palette.config;

import com.study.palette.module.users.annotation.GetUserInfoArgumentResolver;
import com.study.palette.module.users.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final UsersService usersService;

  @Autowired
  public WebConfig(UsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(getUserInfoArgumentResolver());
  }

  @Bean
  public GetUserInfoArgumentResolver getUserInfoArgumentResolver() {
    return new GetUserInfoArgumentResolver(usersService);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*");
  }
}