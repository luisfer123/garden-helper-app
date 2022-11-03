package com.garden.helper.services;

import com.garden.helper.data.entities.Plant;
import com.garden.helper.data.entities.User;

public interface SecurityService {
	
	/**
	 * 
	 * @param userId
	 * @param plantId
	 * @return true if the {@linkplain Plant} with id {@code plantId} is
	 * associated with any {@linkplain User} with id {@code userId}. Returns
	 * false otherwise. User id is a foreign key in the Plant table.
	 */
	boolean plantBelongsToUser(Object objPrincipal, Long plantId);

}
