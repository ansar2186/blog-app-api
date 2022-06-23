package com.ansarlearning.blog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {

	private Integer categoriesId;
	
	@NotBlank(message = "Title can not be Blank")
	@Size(min = 4, message = "Title length shut be 5 charaters minimum")
	private String categoryTitle;
	
	@NotBlank(message = "Desacription can not be blank")
	@Size(min = 10, message = "Description  length shu be 10 characters minumum")
	private String categoryDescription;

	public CategoryDto() {

	}

	public CategoryDto(Integer categoriesId, String categoryTitle, String categoryDescription) {
		super();
		this.categoriesId = categoriesId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}

	public Integer getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(Integer categoriesId) {
		this.categoriesId = categoriesId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
