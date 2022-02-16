package com.ssuarez.blogsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssuarez.blogsystem.dto.PublicationDTO;
import com.ssuarez.blogsystem.dto.PublicationResponse;
import com.ssuarez.blogsystem.entities.Publication;
import com.ssuarez.blogsystem.exceptions.ResourceNotFoundException;
import com.ssuarez.blogsystem.repositories.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public PublicationResponse getAllPublications(int numPage, int sizePage, String sortBy, String sortAddress) {
		
		Sort sort = sortAddress.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : 				Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(numPage, sizePage, sort);

		Page<Publication> publications = publicationRepository.findAll(pageable);

		List<Publication> listPublications = publications.getContent();

		List<PublicationDTO> content = listPublications.stream().map(publication -> mapperDTO(publication))
				.collect(Collectors.toList());

		PublicationResponse publicationResponse = new PublicationResponse();
		publicationResponse.setContentList(content);
		publicationResponse.setNumberPage(publications.getNumber());
		publicationResponse.setSizePage(publications.getSize());
		publicationResponse.setTotalItems(publications.getTotalElements());
		publicationResponse.setTotalPages(publications.getTotalPages());
		publicationResponse.setLastPage(publications.isLast());
		
		return publicationResponse;
	}

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDTO) {
		Publication publication = mapperEntity(publicationDTO);

		Publication newPublication = publicationRepository.save(publication);

		PublicationDTO publicationSave = mapperDTO(newPublication);		

		return publicationSave;
	}

	@Override
	public PublicationDTO findPublicationById(Long id) {
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		return mapperDTO(publication);
	}

	@Override
	public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

		publication.setTittle(publicationDTO.getTittle());
		publication.setDescription(publicationDTO.getDescription());
		publication.setContent(publicationDTO.getContent());

		Publication updatedPublication = publicationRepository.save(publication);

		return mapperDTO(updatedPublication);
	}

	@Override
	public void deletePublication(Long id) {
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

		publicationRepository.delete(publication);
	}

	// Pass entity to DTO
	private PublicationDTO mapperDTO(Publication publication) {
		PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);
		return publicationDTO;
	}

	// Pass DTO to entity
	private Publication mapperEntity(PublicationDTO publicationDTO) {
		Publication publication = modelMapper.map(publicationDTO, Publication.class);
		return publication;
	}

}
