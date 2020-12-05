package com.xlh.cors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("http://shop.z.mukewang.com:8080");
//        config.addAllowedOrigin("http://center.z.mukewang.com:8080");
//        config.addAllowedOrigin("http://shop.z.mukewang.com");
//        config.addAllowedOrigin("http://center.z.mukewang.com");
//        config.addAllowedOrigin("http://www.mtv.com");
//        config.addAllowedOrigin("http://www.mtv.com");
//        config.addAllowedOrigin("http://www.music.com");
//        config.addAllowedOrigin("http://www.music.com");
//        config.addAllowedOrigin("http://www.b.com:8081");
//        config.addAllowedOrigin("http://www.b.com");
//        config.addAllowedOrigin("https://www.b.com");
        // 通过Nginx转发请求，必须在这里设置跨域
        config.addAllowedOrigin("https://www.a.com");
        config.addAllowedOrigin("https://www.b.com");
//        config.addAllowedOrigin("http://www.a.com");
//        config.addAllowedOrigin("*");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的header
        config.addAllowedHeader("*");

//        config.add
        // 2. 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3. 返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }

}
