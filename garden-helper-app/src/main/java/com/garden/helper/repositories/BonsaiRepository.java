package com.garden.helper.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garden.helper.model.entity.Bonsai;

public interface BonsaiRepository extends JpaRepository<Bonsai, Long> {
	
	Page<Bonsai> findAll(Pageable pageable);
	
	Page<Bonsai> findAll(Specification<Bonsai> specification, Pageable pageable);
	
	@Query(value = "select b from Bonsai b join b.user u where u.id = :userId",
			countQuery = "select count(b) from Bonsai b join b.user u where u.id = :userId")
	Page<Bonsai> findAllForGivenUser(@Param("userId") Long userId, Pageable pageable);
	
}
