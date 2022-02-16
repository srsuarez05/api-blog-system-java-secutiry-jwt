package com.ssuarez.blogsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssuarez.blogsystem.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

}
