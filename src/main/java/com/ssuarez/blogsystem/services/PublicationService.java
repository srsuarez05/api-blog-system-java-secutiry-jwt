package com.ssuarez.blogsystem.services;

import com.ssuarez.blogsystem.dto.PublicationDTO;
import com.ssuarez.blogsystem.dto.PublicationResponse;

public interface PublicationService {
	
	public PublicationResponse getAllPublications(int numPage, int sizePage, String sortBy, String sortAddress);
	
	public PublicationDTO createPublication(PublicationDTO publicationDTO);
	
	public PublicationDTO findPublicationById(Long id);
	
	public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);
	
	public void deletePublication(Long id);
}
