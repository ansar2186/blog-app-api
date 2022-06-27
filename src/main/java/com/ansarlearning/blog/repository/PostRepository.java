package com.ansarlearning.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ansarlearning.blog.entity.Categories;
import com.ansarlearning.blog.entity.Post;
import com.ansarlearning.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategories(Categories category);
	
	@Query("select p from Post p where p.title like %:key%")
	List<Post> searchByKeyword(@Param(value = "key") String keyword);

}
