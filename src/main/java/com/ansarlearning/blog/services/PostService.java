package com.ansarlearning.blog.services;

import java.util.List;

import com.ansarlearning.blog.payload.PostDto;
import com.ansarlearning.blog.payload.PostResponse;

public interface PostService {
	// Create Post
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update Post
	public PostDto updatePost(PostDto postDto, Integer postId);

	// deletePost
	public void deletePost(Integer postId);

	
	//used for pagination
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);

	// getPostById
	public PostDto getPostById(Integer postId);

	// get All posts by category
	public List<PostDto> getPostsByCategory(Integer categoryId);

	// get all post by user
	public List<PostDto> getPostsByUser(Integer userId);

	// search post
	public List<PostDto> searchPosts(String keyWord);

}
