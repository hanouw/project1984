/*
package com.jpa4.pj1984.config;

import com.jpa4.pj1984.security.CustomMemberSecurityService;
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
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class MemberSecurityConfig extends ConfigForExtend{

    private final CustomMemberSecurityService customMemberSecurityService;
    private final DataSource dataSource; // yml DB 접속 정보 설정

    // 시큐리티 인증 담당해주는 매니저 빈
//    @Bean
//    public AuthenticationManager memberAuthenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public DaoAuthenticationProvider memberAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customMemberSecurityService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //     비밀번호 암호화를 위한 도구
//    @Bean
//    public BCryptPasswordEncoder memberPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // 시큐리티 통과 조건
    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception{


        http.authenticationProvider(memberAuthenticationProvider());
        http.authorizeHttpRequests(request ->
                                request.requestMatchers("/", "/signup", "/login", "/cms/login", "/cms/signup", "/logout").permitAll()
                                .requestMatchers("/main/**").hasAnyAuthority("STATUS_USER", "STATUS_STORE")
                                .anyRequest().authenticated()
                );

        http.formLogin(login -> login.loginPage("/login")
                .loginProcessingUrl("/login") // 로그인 페이지는 이 페이지야~
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .defaultSuccessUrl("/main/main")) // 로그인 처리 성공했으면
                .rememberMe(remember -> remember.userDetailsService(customMemberSecurityService)
                        .tokenRepository(memberTokenRepository())
                        .tokenValiditySeconds(3600))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 경로는 요청 경로로 지정해서(POST방식) 성공하면 / : 홈으로 돌아가라
                        .logoutSuccessUrl("/") // 홈 : /으로 돌아가라
                        .invalidateHttpSession(true)); // 세션도 지워라

        http.csrf(csrf -> csrf.disable()); // csrf 토큰을 달고 넘어가는데 disable해둠
        return http.build();
    }

    // remember-me 에서 필요 : 시큐리티 자동 로그인 토큰 관리를 위한 빈 등록
    @Bean
    public PersistentTokenRepository memberTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


}
*/
