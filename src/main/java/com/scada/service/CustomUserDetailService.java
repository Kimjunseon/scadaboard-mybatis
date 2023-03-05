package com.scada.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scada.Dto.UserDto;
import com.scada.dao.UserDao;
import com.scada.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	return (UserDetails) userDao.findByUserId(username)
    			.map(this::createUserDetails)
                .orElseThrow(() -> new UserNotFoundException(username + "> 찾을 수 없습니다."));
    }
    
    private UserDetails createUserDetails(UserDto userDto) {
        return User.builder()
                .username(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userDto.getRole().toArray(new String[0]))
                .build();
    }
    
}