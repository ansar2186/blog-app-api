package com.ansarlearning.blog.security;

public class JwtAuthResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtAuthResponse() {
	}

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}

}
