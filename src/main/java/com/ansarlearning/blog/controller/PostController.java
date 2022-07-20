package com.ansarlearning.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.ansarlearning.blog.config.AppConstant;
import com.ansarlearning.blog.payload.ApiResponse;
import com.ansarlearning.blog.payload.PostDto;
import com.ansarlearning.blog.payload.PostResponse;
import com.ansarlearning.blog.services.FileUploadService;
import com.ansarlearning.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	

	@Autowired
	public PostService postService;

	@Autowired
	public FileUploadService fileUploadService;

	@Value("${project.image}")
	private String path;

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

	// get All Post for pagination
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstant.SORT_DIRECTION, required = false) String sortDirection) {

		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);

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

		return new ApiResponse(AppConstant.POST_SUCCESS_FULLY_DELETED_WITH_ID + postId, true);

	}

	@GetMapping("/post/search/{keyWord}")
	public ResponseEntity<List<PostDto>> searcPostByKeyword(@PathVariable String keyWord) {

		List<PostDto> searchPosts = this.postService.searchPosts(keyWord);

		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);

	}

	@PostMapping("/post/image/upload/postId/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto post = this.postService.getPostById(postId);
		String fileName = this.fileUploadService.uploadImage(path, image);

		post.setImageName(fileName);

		PostDto updatePost = this.postService.updatePost(post, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/post/imageName/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImageByImageName(@PathVariable String imageName ,HttpServletResponse response) throws IOException {
		
		InputStream resources = this.fileUploadService.getResources(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resources,response.getOutputStream())   ;
		
	}

}
