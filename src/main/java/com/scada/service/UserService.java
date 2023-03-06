package com.scada.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scada.Dto.UserDto;
import com.scada.dao.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserDao dao;
    
	public boolean join(UserDto dto) {
        int n = dao.join(dto);
        return n > 0; 
    } 
	
	public String login(String id) {
		System.out.println("id: " + id);
		// DB에 정보 보내기
		String userdto = dao.login(id);
		
		// useDto의 값 여부 확인
		System.out.println("userdto :" + userdto);
		
		
        // 검증

        // 검증된 인증 정보로 JWT 토큰 생성
        // String token = jwtTokenProvider.generateToken();
        // System.out.println("token: " + token);

        return userdto;
	}


}
 