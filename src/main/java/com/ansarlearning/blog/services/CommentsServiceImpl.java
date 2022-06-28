package com.ansarlearning.blog.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ansarlearning.blog.entity.Comments;
import com.ansarlearning.blog.entity.Post;
import com.ansarlearning.blog.exception.ResourceNotFoundException;
import com.ansarlearning.blog.payload.CommentDto;
import com.ansarlearning.blog.repository.CommentsRepository;
import com.ansarlearning.blog.repository.PostRepository;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto cmntDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		Comments commnent = this.mapper.map(cmntDto, Comments.class);

		commnent.setPost(post);

		Comments saveCmnt = this.commentsRepository.save(commnent);

		return this.mapper.map(saveCmnt, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer cmntId) {
		Comments comments = this.commentsRepository.findById(cmntId).orElseThrow(()-> new ResourceNotFoundException("Comments", "Comment Id", cmntId));
		this.commentsRepository.delete(comments);

	}

	@Override
	public CommentDto updateComment(CommentDto cmntDto, Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDto> getAllCommnest() {
		// TODO Auto-generated method stub
		return null;
	}

}
