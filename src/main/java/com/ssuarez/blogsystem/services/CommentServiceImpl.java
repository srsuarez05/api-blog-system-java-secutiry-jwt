package com.ssuarez.blogsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ssuarez.blogsystem.dto.CommentDTO;
import com.ssuarez.blogsystem.entities.Comment;
import com.ssuarez.blogsystem.entities.Publication;
import com.ssuarez.blogsystem.exceptions.BlogException;
import com.ssuarez.blogsystem.exceptions.ResourceNotFoundException;
import com.ssuarez.blogsystem.repositories.CommentRepository;
import com.ssuarez.blogsystem.repositories.PublicationRepository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Override
	public CommentDTO createComment(Long publicationId, CommentDTO commentDTO) {
		Comment comment = mapperEntity(commentDTO);
		
		Publication publication = publicationRepository.findById(publicationId).orElseThrow(
				() -> new ResourceNotFoundException("Publication", "id", publicationId));
		
		comment.setPublication(publication);	
		Comment newComment = commentRepository.save(comment);
		
		return mapperDTO(newComment);
	}

	@Override
	public List<CommentDTO> getAllCommentsById(Long publicationId) {
		List<Comment> comments = commentRepository.findCommentsByPublicationId(publicationId);
		
		return comments.stream().map(comment -> mapperDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(Long publicationId, Long commentId) {
		Publication publication = publicationRepository.findById(publicationId).orElseThrow(
				() -> new ResourceNotFoundException("Publicacion", "id", publicationId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comentario", "id", commentId));
		
		if(comment.getPublication().getId() != publication.getId()) {
			throw new BlogException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}
		
		return mapperDTO(comment);
	}

	@Override
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestComment) {
		Publication publication = publicationRepository.findById(publicationId).orElseThrow(
				() -> new ResourceNotFoundException("Publicacion", "id", publicationId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comentario", "id", commentId));
		
		if(comment.getPublication().getId() != publication.getId()) {
			throw new BlogException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}
		
		comment.setName(requestComment.getName());
		comment.setEmail(requestComment.getEmail());
		comment.setBody(requestComment.getBody());
		
		Comment commentCurrent = commentRepository.save(comment);		
		return mapperDTO(commentCurrent);
		
	}

	@Override
	public void deleteComment(Long publicationId, Long commentId) {
		Publication publication = publicationRepository.findById(publicationId).orElseThrow(
				() -> new ResourceNotFoundException("Publicacion", "id", publicationId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comentario", "id", commentId));
		
		if(comment.getPublication().getId() != publication.getId()) {
			throw new BlogException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}
		
		commentRepository.delete(comment);
		
	}
	
	private CommentDTO mapperDTO(Comment comment) {
		CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
		
		return commentDTO;
	}
	
	private Comment mapperEntity(CommentDTO commentDTO) {
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		
		return comment;
	}

}
