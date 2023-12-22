package com.travelsketch.travel.config;

import com.travelsketch.travel.interceptor.LoggingInterceptor;
import com.travelsketch.travel.interceptor.query.ApiQueryCounter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(3000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor(apiQueryCounter));
    }
}
