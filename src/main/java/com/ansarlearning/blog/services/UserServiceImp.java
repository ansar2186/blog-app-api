package com.ansarlearning.blog.services;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
		UserDto userToDto = this.userToDto(updatedUser);

		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User getUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
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
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		this.userRepository.delete(getUser);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);

		/*
		 * user.setId(userDto.getId());
		 * 
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */

		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.mapper.map(user, UserDto.class);

		/*
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setEmail(user.getEmail()); userDto.setPassword(user.getPassword());
		 * userDto.setAbout(user.getAbout());
		 */

		return userDto;
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
