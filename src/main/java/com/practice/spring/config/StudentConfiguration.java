package com.practice.spring.config;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class StudentConfiguration {
    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .requestMatchers("/actuator/**","/security/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(corsFilter(), BasicAuthenticationFilter.class)
                .httpBasic();
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Replace with your allowed origins
        config.addAllowedHeader("*");  // You can specify specific headers here
        config.addAllowedMethod("*");  // You can specify specific HTTP methods here

        source.registerCorsConfiguration("/**", config); // Apply this configuration to all paths
        return new CorsFilter(source);
    }


    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    public FilterRegistrationBean<LogFilter> loggingFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();

        LogFilter logFilter = new LogFilter();
        logFilter.setIncludeQueryString(true);
        logFilter.setIncludePayload(true);
        logFilter.setIncludeHeaders(true);
        logFilter.setMaxPayloadLength(10000);

        registrationBean.setFilter(logFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
