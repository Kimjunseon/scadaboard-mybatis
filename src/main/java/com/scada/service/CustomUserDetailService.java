package com.scada.service;


import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scada.Dto.UserDto;
import com.scada.dao.UserDao;
import com.scada.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String Id) throws UsernameNotFoundException {
    	return (UserDetails) userDao.findByUserId(Id)
                .orElseThrow(() -> new UserNotFoundException(Id + "> 찾을 수 없습니다."));
    }
    
}