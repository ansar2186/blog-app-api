package com.ansarlearning.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ansarlearning.blog.entity.Post;
import com.ansarlearning.blog.repository.PostRepository;

@SpringBootTest
class BlogAppApiApplicationTests {
	
	@Autowired
	public PostRepository postRepository;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void findPostTestCase() {
		List<Post> findAll = this.postRepository.findAll();
		assertThat(findAll).size().isGreaterThan(0);
		
	}


}
