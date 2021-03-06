package com.ssuarez.blogsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssuarez.blogsystem.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Optional<Role> findByName(String name);

}
