package com.init.Challenge.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.init.Challenge.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	public User findByUsername(String username);
	
}
