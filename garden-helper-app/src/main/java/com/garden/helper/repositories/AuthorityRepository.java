package com.garden.helper.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garden.helper.model.entity.Authority;
import com.garden.helper.model.enums.EAuthority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	@Query("select a from Authority a join a.users u where u.username = :username")
	Set<Authority> findUserAuthoritiesByUsername(@Param("username") String username);
	
	Optional<Authority> findByName(EAuthority name);
	
}
