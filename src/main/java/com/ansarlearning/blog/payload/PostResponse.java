package com.ansarlearning.blog.payload;

import java.util.List;

public class PostResponse {

	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPage;
	private boolean isLastPage;

	private List<PostDto> content;

	public PostResponse() {
	}

	public PostResponse(Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPage, boolean isLastPage,
			List<PostDto> content) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPage = totalPage;
		this.isLastPage = isLastPage;
		this.content = content;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	
	

	
}
