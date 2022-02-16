package com.ssuarez.blogsystem.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ssuarez.blogsystem.entities.Comment;

public class PublicationDTO {

	private Long id;
	
	@NotEmpty
	@Size(min=1, message= "El titulo de la publicación deberia tener al menos 1 caracter")
	private String tittle;
	
	@NotEmpty
	@Size(min=10, message= "La descripción de la publicación deberia tener al menos 10 caracteres")	
	private String description;
	
	@NotEmpty(message = "El contenido no debe ser vacio o nulo")
	private String content;
	
	private Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public PublicationDTO() {
		super();
	}	 
	
}
