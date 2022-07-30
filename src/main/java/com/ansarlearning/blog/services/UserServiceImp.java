package com.ansarlearning.blog.services;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.config.AppConstant;
import com.ansarlearning.blog.entity.User;
import com.ansarlearning.blog.entity.UserRole;
import com.ansarlearning.blog.exception.ResourceNotFoundException;
import com.ansarlearning.blog.payload.UserDto;
import com.ansarlearning.blog.repository.RoleRepo;
import com.ansarlearning.blog.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		user.setPassword(this.encoder.encode(userDto.getPassword()));
		User savesUser = this.userRepository.save(user);
		return this.userToDto(savesUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		User updateUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		updateUser.setAbout(user.getAbout());
		User updatedUser = this.userRepository.save(updateUser);

		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User getUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		return this.userToDto(getUser);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAll = this.userRepository.findAll();

		return findAll.stream().map(this::userToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User getUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		this.userRepository.delete(getUser);

	}

	public User dtoToUser(UserDto userDto) {
		return this.mapper.map(userDto, User.class);
	}

	public UserDto userToDto(User user) {
		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = mapper.map(userDto, User.class);
		user.setPassword(this.encoder.encode(userDto.getPassword()));

		UserRole userRole = roleRepo.findById(AppConstant.NORMAL_USER).get();

		user.getUserRole().add(userRole);

		User savedUser = this.userRepository.save(user);

		return mapper.map(savedUser, UserDto.class);
	}
}
