package com.ssuarez.blogsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String nameResource;
	private String nameField;
	private long valueField;
	
	public ResourceNotFoundException(String nameResource, String nameField, long valueField) {
		super(String.format("%s no encontrada con %s : '%s'", nameResource, nameField, valueField));
		this.nameResource = nameResource;
		this.nameField = nameField;
		this.valueField = valueField;
	}

	public String getNameResource() {
		return nameResource;
	}

	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public long getValueField() {
		return valueField;
	}

	public void setValueField(long valueField) {
		this.valueField = valueField;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
