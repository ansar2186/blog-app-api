package com.ansarlearning.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ansarlearning.blog.entity.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {

	

}
