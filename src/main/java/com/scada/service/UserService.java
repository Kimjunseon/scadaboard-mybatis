package com.scada.service;

import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scada.Dto.UserDto;
import com.scada.config.jwt.JwtTokenProvider;
import com.scada.dao.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserDao dao;
	private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    
	public boolean join(UserDto dto) {
        int n = dao.join(dto);
        return n > 0;
    }
	
	public String login(String id, String password) {
		System.out.println("id: " + id);
		System.out.println("password: " + password);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);
		System.out.println("authenticationToken: " + authenticationToken);
        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 검증된 인증 정보로 JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println("token: " + token);

        return token;
	}

}
