package com.ansarlearning.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
	public String resourceName;
	public String filedName;
	public Integer fieldValue;

	public String fieldValue2;

	public ResourceNotFoundException(String resourceName, String filedName, Integer fieldValue) {
		super(String.format("%s not found  with %s : %s", resourceName, filedName, fieldValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String filedName, String fieldValue2) {
		super(String.format("%s not found  with %s : %s", resourceName, filedName, fieldValue2));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue2 = fieldValue2;
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

	public String getFieldValue2() {
		return fieldValue2;
	}

	public void setFieldValue2(String fieldValue2) {
		this.fieldValue2 = fieldValue2;
	}

}