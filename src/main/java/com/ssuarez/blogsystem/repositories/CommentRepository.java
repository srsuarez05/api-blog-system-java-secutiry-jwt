package com.ssuarez.blogsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssuarez.blogsystem.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findCommentsByPublicationId(Long publicationId);
}
