package com.ssuarez.blogsystem.services;

import java.util.List;

import com.ssuarez.blogsystem.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComment(Long publicationId, CommentDTO commentDTO);
	
	public List<CommentDTO> getAllCommentsById(Long publicationId);
	
	public CommentDTO getCommentById(Long publicationId, Long commentId);
	
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestComment);
	
	public void deleteComment(Long publicationId, Long commentId);
	
}
