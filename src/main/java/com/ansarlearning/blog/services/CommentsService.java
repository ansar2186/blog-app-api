package com.ansarlearning.blog.services;

import java.util.List;

import com.ansarlearning.blog.entity.Comments;
import com.ansarlearning.blog.payload.CommentDto;

public interface CommentsService {

	// create comment
	public CommentDto createComment(CommentDto cmntDto,Integer postId);

	// delete comments by comments Id
	public void deleteComment(Integer cmntId);

	// update Comments
	public CommentDto updateComment(CommentDto cmntDto, Integer commentId);

	// Get All comments
	public List<CommentDto> getAllCommnest();

}
