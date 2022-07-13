package com.ansarlearning.blog.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.entity.User;
import com.ansarlearning.blog.exception.ResourceNotFoundException;
import com.ansarlearning.blog.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.repository.findUserByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", username));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}
