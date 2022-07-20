package com.ansarlearning.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansarlearning.blog.config.AppConstant;
import com.ansarlearning.blog.payload.ApiResponse;
import com.ansarlearning.blog.payload.CommentDto;
import com.ansarlearning.blog.services.CommentsService;

@RestController
@RequestMapping("/api")
public class CommentsController {

	
	@Autowired
	public CommentsService commentsService;

	@PostMapping("/comment/postId/{postId}")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

		CommentDto cmntDto = commentsService.createComment(commentDto, postId);

		return new ResponseEntity<CommentDto>(cmntDto, HttpStatus.CREATED);

	}

	@DeleteMapping("/comment/cmntId/{cmntId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer cmntId) {

		this.commentsService.deleteComment(cmntId);

		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.COMMENT_DELETED_MESSAGE, true), HttpStatus.OK);

	}

}
