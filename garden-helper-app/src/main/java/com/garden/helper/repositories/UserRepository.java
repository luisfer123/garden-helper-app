package com.garden.helper.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garden.helper.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findById(Long id);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
