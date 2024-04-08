package com.jpa4.pj1984.config;

import com.jpa4.pj1984.security.CustomCmsSecurityService;
import com.jpa4.pj1984.security.CustomLoginFailureHandler;
import com.jpa4.pj1984.security.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class StoreSecurityConfig extends ConfigForExtend{

//    private final CustomCmsSecurityService customCmsSecurityService;
//    private final DataSource dataSource; // yml DB 접속 정보 설정



    // 시큐리티 통과 조건
    @Bean
    public SecurityFilterChain storeFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable()); // csrf 토큰을 달고 넘어가는데 disable해둠
        http.authorizeHttpRequests(request -> // anyRequest(): 어떤 요청이든 permitAll(): 다 허용
                                request.requestMatchers("/", "/cms/signup", "/cms/login", "/cms/logout", "/login").permitAll()
                                .requestMatchers("/cms/**").hasAuthority("STATUS_STORE") // authenticated는 사용자 정보를 기반으로 사용자가 인증되었는지 확인 //hasAnyRole을 할 경우에는 앞에 ROLE_~~ 로 시작해야함
                                .anyRequest().authenticated()
                );

        http.formLogin(login -> login.loginPage("/cms/login") // 로그인 페이지는 이 페이지야~
                .usernameParameter("storeLoginId")
                .passwordParameter("storePassword")
                        .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                        .failureHandler(new CustomLoginFailureHandler()))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/cms/logout")) // 로그아웃 경로는 요청 경로로 지정해서(POST방식) 성공하면 / : 홈으로 돌아가라
                .logoutSuccessUrl("/") // 홈 : /으로 돌아가라
                .invalidateHttpSession(true)); // 세션도 지워라

        return http.build();
    }


}
