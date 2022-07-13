package com.ansarlearning.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ansarlearning.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();

		ApiResponse apiResponse = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> respMap= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			respMap.put(filedName, message);
			
		});
		return new ResponseEntity<Map<String,String>>(respMap,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse> invalidInvalidCredentialsException(InvalidCredentialsException exception){
		String message = exception.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		
	}

}
