package com.ansarlearning.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansarlearning.blog.exception.InvalidCredentialsException;
import com.ansarlearning.blog.payload.JwtAuthRequest;
import com.ansarlearning.blog.payload.UserDto;
import com.ansarlearning.blog.security.JWTTokenHelper;
import com.ansarlearning.blog.security.JwtAuthResponse;
import com.ansarlearning.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelper helper;

	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest authRequest){

		this.authenticate(authRequest.getUserName(), authRequest.getPassword());

		UserDetails userDetails = this.detailsService.loadUserByUsername(authRequest.getUserName());
		String token = this.helper.generateToken(userDetails);

		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);

		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}

	private void authenticate(String userName, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}

	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> userRegister(@RequestBody UserDto userDto){
		
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
		
	}

}
