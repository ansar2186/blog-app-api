package com.ansarlearning.blog.services;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.entity.User;
import com.ansarlearning.blog.payload.UserDto;
import com.ansarlearning.blog.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savesUser = this.userRepository.save(user);
		return this.userToDto(savesUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		User updateUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResolutionException("User Not Found: " + userId));

		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		updateUser.setAbout(user.getAbout());
		User updatedUser = this.userRepository.save(updateUser);
		UserDto userToDto = this.userToDto(updatedUser);

		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User getUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResolutionException("User Not Found: " + userId));
		UserDto userToDto = this.userToDto(getUser);
		return userToDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAll = this.userRepository.findAll();

		List<UserDto> userDtoList = findAll.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		/*
		 * List<UserDto> list= new ArrayList<>();
		 * 
		 * for (User user : findAll) {
		 * 
		 * UserDto userToDto = this.userToDto(user); list.add(userToDto);
		 * 
		 * }
		 */

		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User getUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResolutionException("User Not Found: " + userId));
		this.userRepository.delete(getUser);

	}

	public User dtoToUser(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getId());

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());

		return userDto;
	}
}
