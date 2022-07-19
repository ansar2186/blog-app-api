package com.ansarlearning.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_role")
public class UserRole {
	
	@Id
	@Column(name = "role_id")
	private Integer roleId;
	
	private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRole(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public UserRole() {
	}
	
	
	
	
	
	

}
