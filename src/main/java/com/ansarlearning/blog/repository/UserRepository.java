package com.ansarlearning.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansarlearning.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findUserByEmail(String email);

}
