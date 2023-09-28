package com.study.palette.config;

import com.study.palette.module.user.RoleCheckFilter;
import com.study.palette.module.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final UserService userService;

    @Autowired
    public FilterConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public FilterRegistrationBean<RoleCheckFilter> myAuthorizationFilter() {
        FilterRegistrationBean<RoleCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RoleCheckFilter(userService));
        registrationBean.addUrlPatterns("/api/users/*"); // 필터를 거칠 url patterns
        return registrationBean;
    }

}
