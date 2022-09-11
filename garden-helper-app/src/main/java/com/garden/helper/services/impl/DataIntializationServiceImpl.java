package com.garden.helper.services.impl;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.garden.helper.exceptions.UserNotFoundException;
import com.garden.helper.model.entity.Authority;
import com.garden.helper.model.entity.Bonsai;
import com.garden.helper.model.entity.Bonsai;
import com.garden.helper.model.entity.Bonsai;
import com.garden.helper.model.entity.User;
import com.garden.helper.model.enums.EAuthority;
import com.garden.helper.model.enums.EBonsaiStyle;
import com.garden.helper.model.enums.EBonsaiType;
import com.garden.helper.repositories.AuthorityRepository;
import com.garden.helper.repositories.BonsaiRepository;
import com.garden.helper.repositories.UserRepository;

@Service
public class DataIntializationServiceImpl {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	@Autowired
	private BonsaiRepository bonsaiRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@EventListener(classes = ApplicationReadyEvent.class)
	public void onInit() {
		
		this.addAuthorities();
		this.addUsers();
		this.addBonsais();
		
	}
	
	@Transactional
	public void addAuthorities() {
		if(authRepo.count() == 0) {
			Authority roleUser = new Authority(EAuthority.ROLE_USER);
			Authority roleMode = new Authority(EAuthority.ROLE_MODERATOR);
			Authority roleAdmin = new Authority(EAuthority.ROLE_ADMIN);
			
			authRepo.saveAll(Arrays.asList(roleUser, roleMode, roleAdmin));
		}
	}
	
	@Transactional
	public void addUsers() {
		if(userRepo.count() == 0) {
			
			Authority roleUser = null;
			Authority roleMode = null;
			Authority roleAdmin = null;
			
			try {
				roleUser = authRepo.findByName(EAuthority.ROLE_USER).get();
				roleMode = authRepo.findByName(EAuthority.ROLE_MODERATOR).get();
				roleAdmin = authRepo.findByName(EAuthority.ROLE_ADMIN).get();
			} catch(Exception e) {
				
			}
			
			User user = new User(
					"user",
					encoder.encode("password"),
					"user@mail.com",
					"ufirstname",
					"umiddlename",
					"ulastname",
					"uslastname"
					);
			user.addAuthority(roleUser);
			
			User mode = new User(
					"moderator",
					encoder.encode("password"),
					"moderator@mail.com",
					"mfirstname",
					"mmiddlename",
					"mlastname",
					"mslastname"
					);
			mode.addAuthority(roleUser);
			mode.addAuthority(roleMode);
			
			User admin = new User(
					"admin",
					encoder.encode("password"),
					"admin@mail.com",
					"afirstname",
					"amiddlename",
					"alastname",
					"aslastname"
					);
			admin.addAuthority(roleUser);
			admin.addAuthority(roleMode);
			admin.addAuthority(roleAdmin);
			
			userRepo.saveAll(Arrays.asList(user, mode, admin));
		}
	}
	
	@Transactional
	public void addBonsais() {
		
		if(bonsaiRepo.count() == 0) {
			
			User admin = userRepo.findByUsername("admin")
					.orElseThrow(() -> new UserNotFoundException("User not found."));
			User mode = userRepo.findByUsername("moderator")
					.orElseThrow(() -> new UserNotFoundException("User not found."));
			User user = userRepo.findByUsername("user")
					.orElseThrow(() -> new UserNotFoundException("User not found."));
			
			// User Bonsais
			
			Bonsai userBonsai1 = new Bonsai();
			userBonsai1.setName("userBonsai1");
			userBonsai1.setCreationDate(new Timestamp(System.currentTimeMillis()));
			userBonsai1.setType(EBonsaiType.BONSAI);
			userBonsai1.setStyle(EBonsaiStyle.MOYOGI);
			user.addPlant(userBonsai1);
			
			Bonsai userBonsai2 = new Bonsai();
			userBonsai2.setName("userBonsai2");
			userBonsai2.setCreationDate(new Timestamp(System.currentTimeMillis()-1200));
			userBonsai2.setType(EBonsaiType.PRE_BONSAI);
			userBonsai2.setStyle(EBonsaiStyle.SHAKAN);
			user.addPlant(userBonsai2);
			
			Bonsai userBonsai3 = new Bonsai();
			userBonsai3.setName("userBonsai3");
			userBonsai3.setCreationDate(new Timestamp(System.currentTimeMillis()-3200));
			userBonsai3.setType(EBonsaiType.BONSAI);
			userBonsai3.setStyle(EBonsaiStyle.CHOKAN);
			user.addPlant(userBonsai3);
			
			// Moderator Bonsais
			
			Bonsai modeBonsai1 = new Bonsai();
			modeBonsai1.setName("modeBonsai1");
			modeBonsai1.setCreationDate(new Timestamp(System.currentTimeMillis()));
			modeBonsai1.setType(EBonsaiType.PRE_BONSAI);
			modeBonsai1.setStyle(EBonsaiStyle.MOYOGI);
			mode.addPlant(modeBonsai1);
			
			Bonsai modeBonsai2 = new Bonsai();
			modeBonsai2.setName("modeBonsai2");
			modeBonsai2.setCreationDate(new Timestamp(System.currentTimeMillis()-1200));
			modeBonsai2.setType(EBonsaiType.BONSAI);
			modeBonsai2.setStyle(EBonsaiStyle.SHAKAN);
			mode.addPlant(modeBonsai2);
			
			Bonsai modeBonsai3 = new Bonsai();
			modeBonsai3.setName("modeBonsai3");
			modeBonsai3.setCreationDate(new Timestamp(System.currentTimeMillis()-3200));
			modeBonsai3.setType(EBonsaiType.PRE_BONSAI);
			modeBonsai3.setStyle(EBonsaiStyle.CHOKAN);
			mode.addPlant(modeBonsai3);
			
			// Admin Bonsais
			
			Bonsai adminBonsai1 = new Bonsai();
			adminBonsai1.setName("adminBonsai1");
			adminBonsai1.setCreationDate(new Timestamp(System.currentTimeMillis()));
			adminBonsai1.setType(EBonsaiType.PRE_BONSAI);
			adminBonsai1.setStyle(EBonsaiStyle.MOYOGI);
			admin.addPlant(adminBonsai1);
			
			Bonsai adminBonsai2 = new Bonsai();
			adminBonsai2.setName("adminBonsai2");
			adminBonsai2.setCreationDate(new Timestamp(System.currentTimeMillis()-1200));
			adminBonsai2.setType(EBonsaiType.BONSAI);
			adminBonsai2.setStyle(EBonsaiStyle.SHAKAN);
			admin.addPlant(adminBonsai2);
			
			Bonsai adminBonsai3 = new Bonsai();
			adminBonsai3.setName("adminBonsai3");
			adminBonsai3.setCreationDate(new Timestamp(System.currentTimeMillis()-3200));
			adminBonsai3.setType(EBonsaiType.BONSAI);
			adminBonsai3.setStyle(EBonsaiStyle.CHOKAN);
			admin.addPlant(adminBonsai3);
			
			bonsaiRepo.saveAll(Arrays.asList(
					userBonsai1, userBonsai2, userBonsai3,
					modeBonsai1, modeBonsai2, modeBonsai3,
					adminBonsai1, adminBonsai2, adminBonsai3));
		}
	}

}
