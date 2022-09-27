package com.garden.helper.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.garden.helper.data.entities.Authority;
import com.garden.helper.data.entities.User;
import com.garden.helper.data.enums.EAuthority;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthorityRepositoryTest {
	
	private static final String usernameTestUser = "user_test";
	private static final String passwordTestUser = "password";
	
	private static final String usernameTestAdmin = "admin_test";
	private static final String passwordTestAdmin = "password";
	private static final String emailTestAdmin = "admin@mail.com";
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Test
	public void shouldFindUserAuthoritiesByUsername() {
		User testAdmin = new User();
		testAdmin.setUsername(usernameTestAdmin);
		testAdmin.setPassword(passwordTestAdmin);
		testAdmin.setEmail(emailTestAdmin);
		testAdmin.addAuthority(new Authority(EAuthority.ROLE_USER));
		testAdmin.addAuthority(new Authority(EAuthority.ROLE_ADMIN));
		
		em.persist(testAdmin);
		
		assertThat(authRepo.findUserAuthoritiesByUsername(usernameTestAdmin))
			.extracting("name")
			.containsExactlyInAnyOrder(
					EAuthority.ROLE_USER,
					EAuthority.ROLE_ADMIN);
		
		User testUser = new User();
		testUser.setUsername(usernameTestUser);
		testUser.setPassword(passwordTestUser);
		testUser.setEmail(passwordTestUser);
		testUser.addAuthority(new Authority(EAuthority.ROLE_USER));
		
		em.persist(testUser);
		
		assertThat(authRepo.findUserAuthoritiesByUsername(usernameTestUser))
			.extracting("name")
			.containsExactlyInAnyOrder(EAuthority.ROLE_USER);
	}

}
