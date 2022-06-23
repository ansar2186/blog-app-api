package com.ansarlearning.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbk_categories")
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoriesId;

	@Column(name = "title")
	private String categoryTitle;

	@Column(name = "description")
	private String categoryDescription;

	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();

	public Categories() {
	}

	public Categories(Integer categoriesId, String categoryTitle, String categoryDescription, List<Post> postList) {
		super();
		this.categoriesId = categoriesId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		this.postList = postList;
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

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

}
