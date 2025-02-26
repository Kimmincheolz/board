package com.mc.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 보호 비활성화 설정 변경
        http

                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/", "/login", "/signup").permitAll()  // 로그인, 회원가입 페이지는 누구나 접근 가능
                        .requestMatchers("/check-id", "/check-email").permitAll()  // 특정 URL에 대한 접근 허용
                        .requestMatchers("admin/**").hasRole("ADMIN")  // /admin URL은 ADMIN 권한을 가진 사람만 접근
                        .anyRequest().authenticated()  // 나머지 URL은 인증된 사용자만 접근 가능


                ).formLogin((formLogin) -> formLogin
                        .loginPage("/login")  // 커스텀 로그인 페이지
                        .loginProcessingUrl("/login")  // 로그인 처리 URL
                        .usernameParameter("userId")
                        .defaultSuccessUrl("/", true)  // 로그인 성공 후 리다이렉트할 URL
                        .failureUrl("/login/error")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")  // 로그아웃 URL
                        .logoutSuccessUrl("/")  //로그아웃에 성공하면 url설정
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 url
                        .permitAll()
                        .invalidateHttpSession(true)   //세션의 사용을 정지
                )
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**"))
                        .ignoringRequestMatchers("/check-id", "/check-email")  // CSRF를 무시할 URL


                )
                .exceptionHandling((exception)-> exception
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));


        return http.build(); // HttpSecurity 객체를 구성하여 반환






    }
}
