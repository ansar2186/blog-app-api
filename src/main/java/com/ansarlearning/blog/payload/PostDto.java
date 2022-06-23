package com.ansarlearning.blog.payload;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ansarlearning.blog.entity.Categories;
import com.ansarlearning.blog.entity.User;

public class PostDto {

	private Integer postId;
	@NotEmpty
	@Size(min = 3, message = "Title size must be 3 characters")
	private String title;
	@NotEmpty
	@Size(min = 10, message = "Content size must be 3 characters")
	private String content;
	private String imageName;
	private Date createdDate;
	private CategoryDto categories;
	private UserDto user;

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDto(Integer postId, @NotEmpty @Size(min = 3, message = "Title size must be 3 characters") String title,
			@NotEmpty @Size(min = 10, message = "Content size must be 3 characters") String content, String imageName,
			Date createdDate, CategoryDto categories, UserDto user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.createdDate = createdDate;
		this.categories = categories;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public CategoryDto getCategories() {
		return categories;
	}

	public void setCategories(CategoryDto categories) {
		this.categories = categories;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

}
