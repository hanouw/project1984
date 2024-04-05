package com.jpa4.pj1984.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class ConfigForExtend {

    // 시큐리티 필터 처리 무시 경로 지정
    @Bean
    public WebSecurityCustomizer WebSecurityCustomizer() {
        return (web) -> web.ignoring()
//                시큐리티 무시 설정
                .requestMatchers(new AntPathRequestMatcher("/**"))
//                시큐리티 무시 설정
                .requestMatchers(new AntPathRequestMatcher("/css/**"))
                .requestMatchers(new AntPathRequestMatcher("/cms/ajaxUsernameAvail"))
                .requestMatchers(new AntPathRequestMatcher("/img/**"));
    }
    //     비밀번호 암호화를 위한 도구
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
