package com.garden.helper.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BonsaiRepositoryTest {
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private BonsaiRepository bonsaiRepo;
	
	@Test
	public void findAllForGivenUserTest() {
		
	}

}
