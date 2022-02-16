package com.ssuarez.blogsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssuarez.blogsystem.dto.CommentDTO;
import com.ssuarez.blogsystem.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/publications/{publicationId}/comments")
	public List<CommentDTO> listCommentsByPublicationId(@PathVariable(value = "publicationId") Long publicationId) {
		return commentService.getAllCommentsById(publicationId);
	}

	@GetMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "commentId") Long commentId) {
		
		CommentDTO commentDTO = commentService.getCommentById(publicationId, commentId);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}

	@PostMapping("/publications/{publicationId}/comments")
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "publicationId") Long publicationId,
			@Valid @RequestBody CommentDTO commentDTO) {
		
		return new ResponseEntity<>(commentService.createComment(publicationId, commentDTO), HttpStatus.CREATED);
	}

	@PutMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody CommentDTO commentDTO) {
		
		CommentDTO updatedCommentDTO = commentService.updateComment(publicationId, commentId, commentDTO);
		return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
	}

	@DeleteMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "commentId") Long commentId) {
		
		commentService.deleteComment(publicationId, commentId);
		return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
	}

}
