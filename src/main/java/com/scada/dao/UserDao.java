package com.scada.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.scada.Dto.UserDto;

@Mapper
@Repository
public interface UserDao {
	String login(String id);
	int join(UserDto dto);

}
