package com.ssuarez.blogsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDTO {

	private Long id;
	
	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	private String name;
	
	@NotEmpty(message = "El email no debe ser vacio o nulo")
	@Email(message = "Debe tener un email valido")
	private String email;
	
	@NotEmpty
	@Size(min = 10,message = "El cuerpo del comentario debe tener al menos 10 caracteres")
	private String body;
	
	public CommentDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
