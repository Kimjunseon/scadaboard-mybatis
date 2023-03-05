package com.scada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.scada.config.jwt.JwtAuthenticationFilter;
import com.scada.config.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  {
	
	private JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
    }
	
	//authenticationManager Bean 등록, 시용자 인증 정보가 데이터베이스에 저장되어 있으면
	// 데이터베이스를 조회하여 인증을 수행하고, OAuth2 프로토콜을 사용하는 경우 OAuth2 인증 서버와 통신하여 인증을 수행

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 모든 권한에 대한 요청 접근 가능
		http
			.authorizeRequests().anyRequest().permitAll()
			.and()
		// 시큐리티 권한이 없을 때, post 요청이 막힐 때 사용? 
			.csrf().disable()
		// 세션 사용 안함?
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		
		// JwtAuthenticationFilter 먼저 사용
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}