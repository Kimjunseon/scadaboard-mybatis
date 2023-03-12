package com.scada.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scada.Dto.UserDto;
import com.scada.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	public final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody UserDto dto) {
		String rawPassword = dto.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		dto.setPassword(encPassword);
        logger.info("UserController join()");
        boolean b = userService.join(dto);
        logger.info(dto.toString());
        
        if(b) {
        	System.out.println("ok");
            return ResponseEntity.ok().body(dto);
        }
        System.out.println("no");
		return null;
    }
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> checkLogin) {
		System.out.println("checkLogin: " + checkLogin);
		String encodedPassword = userService.login(checkLogin.get("id"));
		boolean encPassword = bCryptPasswordEncoder.matches(checkLogin.get("password"), encodedPassword);
		logger.info("UserController login()");
		return ResponseEntity.ok(encPassword + "");
	}
	
}
