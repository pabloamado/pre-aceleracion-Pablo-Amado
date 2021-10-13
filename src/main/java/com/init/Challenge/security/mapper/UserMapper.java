package com.init.Challenge.security.mapper;

import org.springframework.stereotype.Component;

import com.init.Challenge.security.dto.CredentialsDto;
import com.init.Challenge.security.model.User;

@Component
public class UserMapper {

	public User toUser(CredentialsDto credentials) {
		
		User user=new User();
		
		user.setUsername(credentials.getUsername());
		
		user.setPassword(credentials.getPassword());
	
		return user;
	}
}
