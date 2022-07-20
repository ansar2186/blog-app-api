package com.ansarlearning.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansarlearning.blog.config.AppConstant;
import com.ansarlearning.blog.payload.UserDto;
import com.ansarlearning.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class HomeController {

	
	@Autowired
	private UserService service;
	

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		
		
		UserDto createUser = this.service.createUser(userDto);

		System.out.println("Password------------" + userDto.getPassword());

		return new ResponseEntity<>(createUser, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<UserDto> updatedUser(@RequestBody UserDto userDto) {
		UserDto updateUser = this.service.updateUser(userDto, userDto.getId());

		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer uId) {
		UserDto userById = this.service.getUserById(uId);

		return new ResponseEntity<>(userById, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer uId) {
		this.service.deleteUser(uId);
		return new ResponseEntity<>(Map.of("Message", AppConstant.USER_DELETE_SUCCESSFULLY_WITH_ID + uId), HttpStatus.OK);

	}

	@GetMapping("/get-AllUser")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> allUser = this.service.getAllUser();

		return new ResponseEntity<>(allUser, HttpStatus.OK);

	}

}
