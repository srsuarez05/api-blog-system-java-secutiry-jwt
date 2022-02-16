package com.ssuarez.blogsystem.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ssuarez.blogsystem.dto.PublicationDTO;
import com.ssuarez.blogsystem.dto.PublicationResponse;
import com.ssuarez.blogsystem.services.PublicationService;
import com.ssuarez.blogsystem.utilities.Constants;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PublicationResponse listPublications(
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int numPage,
			@RequestParam(value = "sizePage", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int sizePage,
			@RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY_DEFAULT, required = false) String sortBy,
			@RequestParam(value = "sortAddress", defaultValue = Constants.SORT_DEFAULT_ADDRESS, required = false) String sortAddress){
		return publicationService.getAllPublications(numPage, sizePage, sortBy, sortAddress);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PublicationDTO> getPublicationById(@Valid @PathVariable(value= "id") Long id){
		return ResponseEntity.ok(publicationService.findPublicationById(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO){
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PublicationDTO> updatePublication(@Valid @RequestBody PublicationDTO publicationDTO, @PathVariable(value= "id") Long id){
		PublicationDTO publicationResponse = publicationService.updatePublication(publicationDTO, id);
		
		return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublication(@PathVariable(value = "id") Long id){
		publicationService.deletePublication(id);
		
		return new ResponseEntity<String>("Publication delete success!!", HttpStatus.OK);
	}
	
}
