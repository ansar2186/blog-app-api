package com.ansarlearning.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansarlearning.blog.entity.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Integer> {

}
