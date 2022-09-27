package com.garden.helper.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.garden.helper.controllers.UserRestController;
import com.garden.helper.data.entities.User;
import com.garden.helper.data.models.UserModel;
import com.garden.helper.helpers.ImageHelper;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public UserModelAssembler() {
		this(UserRestController.class, UserModel.class);
	}

	public UserModelAssembler(Class<?> controllerClass, Class<UserModel> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public UserModel toModel(User user) {
		
		UserModel userModel = instantiateModel(user);
		
		userModel.add(linkTo(
				methodOn(UserRestController.class)
				.findUserById(user.getId()))
				.withSelfRel());
		
		byte[] profilePicture = null;
		if(user.getProfilePicture() != null && user.getProfilePicture().length > 0) {
			profilePicture = 
					ImageHelper.decompressBytes(user.getProfilePicture());
		} else {
			Resource imageResource = 
					resourceLoader.getResource("classpath:static/images/user-profile-default-image.jpg");
			try {
				profilePicture = Files.readAllBytes(Paths.get(imageResource.getURI()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		userModel.setId(user.getId());
		userModel.setUsername(user.getUsername());
		userModel.setEmail(user.getEmail());
		userModel.setFirstName(user.getFirstName());
		userModel.setMiddleName(user.getMiddleName());
		userModel.setLastName(user.getLastName());
		userModel.setSecondLastName(user.getSecondLastName());
		// userModel.setProfilePicture(profilePicture);
		
		return userModel;
	}
	
	@Override
	public CollectionModel<UserModel> toCollectionModel(
			Iterable<? extends User> users) {
		
		CollectionModel<UserModel> userModels =
				super.toCollectionModel(users);

		return userModels;
	}

}
