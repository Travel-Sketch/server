package com.travelsketch.travel.config;

import com.travelsketch.travel.interceptor.query.ApiQueryCounter;
import com.travelsketch.travel.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig 설정
 *
 * @author 임우택
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApiQueryCounter apiQueryCounter;

    public WebConfig(ApiQueryCounter apiQueryCounter) {
        this.apiQueryCounter = apiQueryCounter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor(apiQueryCounter));
    }
}
