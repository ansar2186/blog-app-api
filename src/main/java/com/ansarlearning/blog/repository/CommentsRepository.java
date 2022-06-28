package com.ansarlearning.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansarlearning.blog.entity.Comments;


public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
