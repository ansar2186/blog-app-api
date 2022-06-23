package com.ansarlearning.blog.services;

import java.util.List;

import com.ansarlearning.blog.payload.UserDto;

public interface UserService {

	public UserDto createUser(UserDto userDto);

	public UserDto updateUser(UserDto user, Integer userId);

	public UserDto getUserById(Integer userId);

	public List<UserDto> getAllUser();

	public void deleteUser(Integer userId);

}
