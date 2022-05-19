package com.ansarlearning.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDto {

	private int id;
	@javax.validation.constraints.NotEmpty
	@Size(min = 5 ,message = "User Name must be 5 character")
	private String name;
	@Email(message = "Please Enter a valid email address")
	private String email;
	@NotEmpty
	@Size(min = 5,max = 8, message = "Password must be contain minimum 3 character and maximum 8 character")
	private String password;

	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto() {
	}

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

}
