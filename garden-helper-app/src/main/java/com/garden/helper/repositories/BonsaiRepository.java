package com.garden.helper.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garden.helper.data.entities.Bonsai;
import com.garden.helper.data.enums.EBonsaiStyle;

public interface BonsaiRepository extends JpaRepository<Bonsai, Long> {
	
	Page<Bonsai> findAll(Pageable pageable);
	
	Page<Bonsai> findAll(Specification<Bonsai> specification, Pageable pageable);
	
	@Query(value = "select b from Bonsai b join b.user u where u.id = :userId",
			countQuery = "select count(b) from Bonsai b join b.user u where u.id = :userId")
	Page<Bonsai> findAllForGivenUser(@Param("userId") Long userId, Pageable pageable);
	
	/**
	 * Filters the result by including in the {@linkplain Page} only the 
	 * {@linkplain Bonsai}s which belong to given user and with {@code Bonsai.style} 
	 * property matching {@code style} parameter.
	 * 
	 * @param style
	 * @param pageable
	 * @return
	 */
	@Query(value = "select b from Bonsai b join b.user u where u.id = :userId and b.style = :style",
			countQuery = "select count(b) from Bonsai b join b.user u where u.id = :userId and b.style = :style")
	Page<Bonsai> findByStyleForGivenUser(@Param("userId") Long userId, @Param("style") EBonsaiStyle style, Pageable pageable);
	
	List<Bonsai> findByUserId(Long userId);
	
}
