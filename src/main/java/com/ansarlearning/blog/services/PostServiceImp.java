package com.ansarlearning.blog.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.entity.Categories;
import com.ansarlearning.blog.entity.Post;
import com.ansarlearning.blog.entity.User;
import com.ansarlearning.blog.exception.ResourceNotFoundException;
import com.ansarlearning.blog.payload.PostDto;
import com.ansarlearning.blog.payload.PostResponse;
import com.ansarlearning.blog.repository.CategoryRepo;
import com.ansarlearning.blog.repository.PostRepository;
import com.ansarlearning.blog.repository.UserRepository;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	public PostRepository postRepository;

	@Autowired
	public ModelMapper mapper;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		Categories categories = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));

		Post post = this.mapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setCreatedDate(new Date());

		post.setUser(user);
		post.setCategories(categories);

		Post savePost = this.postRepository.save(post);

		return this.mapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post savePost = this.postRepository.save(post);
		PostDto psotDto = this.mapper.map(savePost, PostDto.class);

		return psotDto;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
		this.postRepository.delete(post);

	}

	/*
	 * @Override public List<PostDto> getAllPost() { List<Post> findAll =
	 * this.postRepository.findAll(); List<PostDto> postDto =
	 * findAll.stream().map((posts) -> this.mapper.map(posts, PostDto.class))
	 * .collect(Collectors.toList()); return postDto; }
	 */

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
		PostDto postDto = this.mapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Categories cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));
		List<Post> posts = this.postRepository.findByCategories(cat);
		List<PostDto> postDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));
		List<Post> findByUser = this.postRepository.findByUser(user);
		List<PostDto> postDto = findByUser.stream().map((userList) -> this.mapper.map(userList, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyWord) {
		List<Post> searchPosts = this.postRepository.searchByKeyword(keyWord);
		List<PostDto> postDtoList = searchPosts.stream().map((posts -> this.mapper.map(posts, PostDto.class)))
				.collect(Collectors.toList());

		return postDtoList;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		// using ternpry operator
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		/*
		 * if (sortDirection.equalsIgnoreCase("asc")) {
		 * 
		 * sort = Sort.by(sortBy).ascending();
		 * 
		 * } else { sort = Sort.by(sortBy).descending(); }
		 */
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepository.findAll(pageable);

		List<Post> allPosts = pagePost.getContent();

		// List<Post> findAll = this.postRepository.findAll();
		List<PostDto> postDto = allPosts.stream().map((posts) -> this.mapper.map(posts, PostDto.class))
				.collect(Collectors.toList());

		PostResponse response = new PostResponse();
		response.setContent(postDto);
		response.setPageNumber(pagePost.getNumber());
		response.setPageSize(pagePost.getSize());
		response.setTotalElements(pagePost.getTotalElements());
		response.setTotalPage(pagePost.getTotalPages());
		response.setLastPage(pagePost.isLast());

		return response;
	}

}
