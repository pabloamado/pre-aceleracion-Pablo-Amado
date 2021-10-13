package com.init.Challenge.security.dto;

public class JwtResponse {

	public String jwt;

	public JwtResponse(String jwt) {
		this.jwt=jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
