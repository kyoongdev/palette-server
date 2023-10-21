package com.study.palette.config;

import com.study.palette.module.user.GetUserInfoArgumentResolver;
import com.study.palette.module.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;

    @Autowired
    public WebConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getUserInfoArgumentResolver());
    }

    @Bean
    public GetUserInfoArgumentResolver getUserInfoArgumentResolver() {
        return new GetUserInfoArgumentResolver(userService);
    }
}