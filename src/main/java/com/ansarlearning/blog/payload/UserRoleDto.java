package com.ansarlearning.blog.payload;

public class UserRoleDto {

	private Integer id;
	private String name;

	public UserRoleDto() {
	}

	public UserRoleDto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
