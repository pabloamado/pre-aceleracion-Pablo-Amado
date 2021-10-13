package com.init.Challenge.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.init.Challenge.exception.RegisterException;
import com.init.Challenge.exception.msg.ExceptionMsg;
import com.init.Challenge.security.dto.CredentialsDto;
import com.init.Challenge.security.mapper.UserMapper;
import com.init.Challenge.security.model.User;
import com.init.Challenge.security.repository.UserRepository;
import com.init.Challenge.service.EmailService;

import java.io.IOException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		com.init.Challenge.security.model.User user=userRepository.findByUsername(username);
		
		if(user!=null) {
			
			return new org.springframework.security.core.userdetails.User
					(user.getUsername(),user.getPassword(),Collections.emptyList());
		}
		
		throw new UsernameNotFoundException(ExceptionMsg.USER_PASSWORD_NOT_FOUND);
		
	}

	public void registerUser(CredentialsDto credentialsDto) throws IOException {
		
			User user=userMapper.toUser(credentialsDto);
			
			User userRecovered=userRepository.findByUsername(user.getUsername());
			
			if(userRecovered!=null) {
				
				throw new RegisterException(ExceptionMsg.USER_EXISTS);
			}
			
			user=userRepository.save(user);
			
			if(user!=null) {
				
				emailService.sendWelcomeEmail(user.getUsername());
			}
			
	}

}
