package com.ansarlearning.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
	public String resourceName;
	public String filedName;
	public Integer fieldValue;

	public ResourceNotFoundException(String resourceName, String filedName, Integer fieldValue) {
		super(String.format("%s not found  with %s : %s",resourceName,filedName,fieldValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}
	
	
	

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public Integer getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Integer fieldValue) {
		this.fieldValue = fieldValue;
	}

}