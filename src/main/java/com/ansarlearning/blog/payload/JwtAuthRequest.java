package com.ansarlearning.blog.payload;

public class JwtAuthRequest {

	private String userName;

	private String password;

	public JwtAuthRequest() {
	}

	public JwtAuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
