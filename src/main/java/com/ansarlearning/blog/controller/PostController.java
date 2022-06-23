package com.ansarlearning.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ansarlearning.blog.payload.ApiResponse;
import com.ansarlearning.blog.payload.PostDto;
import com.ansarlearning.blog.payload.PostResponse;
import com.ansarlearning.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	public PostService postService;

	@PostMapping("/user/{userID}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userID,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userID, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// get Post By User

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {

		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	// get Post By Category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {

		List<PostDto> postsByUser = this.postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	/*
	 * // get All Post
	 * 
	 * @GetMapping("/posts") public ResponseEntity<List<PostDto>> getAllPost() {
	 * 
	 * List<PostDto> allPost = this.postService.getAllPost();
	 * 
	 * return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	 * 
	 * }
	 */

	// get All Post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {

		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize);

		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

	}

	// getPost By PostId
	@GetMapping("/postId/{PostId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer PostId) {
		PostDto postById = this.postService.getPostById(PostId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);

	}

	// update Post
	@PutMapping("/post/{postID}/post-update")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postID) {

		PostDto updatePost = this.postService.updatePost(postDto, postID);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// delete Post
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {

		this.postService.deletePost(postId);

		return new ApiResponse("Post SuccessFully Deleted with Id: " + postId, true);

	}

}
