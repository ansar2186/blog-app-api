package com.ansarlearning.blog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ansarlearning.blog.entity.Post;
import com.ansarlearning.blog.entity.User;
import com.ansarlearning.blog.repository.PostRepository;
import com.ansarlearning.blog.repository.UserRepository;

@SpringBootTest
class BlogAppApiApplicationTests {
	
	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void findPostTestCase() {
		List<Post> findAll = this.postRepository.findAll();
		assertThat(findAll).size().isPositive();
		
	}
	
	@Test
	void testUserCreate() {
		User user= new User();
		//user.setId(105);
		user.setEmail("Johan.pitter@dxc.com");
		user.setName("Johan");
		user.setAbout("IT");
		user.setPassword("johan123");
		userRepository.save(user);	
		assertNotNull(userRepository.findUserByEmail("Johan.pitter@dxc.com").getClass());
		
	}

	@Test
	void testFindAllUser() {
		
		List<User> findAll = userRepository.findAll();
		assertThat(findAll).size().isPositive();
		
	}

}
