package com.garden.helper.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garden.helper.data.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {
	
	Optional<Plant> findById(Long plantId);
	
	List<Plant> findByUserId(Long userId);
	
	/**
	 * 
	 * @param plantId
	 * @param userId
	 * @return true if the {@linkplain Plant} with id {@code plantId} is
	 * associated with any {@linkplain User} with id {@code userId}. Returns
	 * false otherwise. User id is a foreign key in the Plant table.
	 */
	@Query(value = "select case when count(p) > 0 then true else false end "
			+ "from Plant p join p.user u where p.id = :plantId and u.id = :userId")
	boolean belongsToGivenUser(@Param("plantId") Long plantId, @Param("userId") Long userId);

}
