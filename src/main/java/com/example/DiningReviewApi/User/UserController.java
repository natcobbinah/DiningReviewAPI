package com.example.DiningReviewApi.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.DiningReviewApi.DataModels.UserDTO;
import com.example.DiningReviewApi.DataModels.UserSearchDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Operation(summary = "Creates a new user record into the system", description = "Creates a new User registered account in  the system", 
			tags = {"User"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User Account successfully created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid characters in username", content = @Content) })
	@PostMapping("/user")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "User created Successfully")
	public User createUser(@RequestBody UserDTO userDataModel) {
		return userService.createUser(userDataModel);
	}

	@Operation(summary = "update existing user record into the system", description = "updates a User registered account in  the system",
			tags = {"User"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User updated successfully created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid characters in username", content = @Content) })
	@PutMapping("/user")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "User updated Successfully")
	public User updateUserProfile(@RequestBody UserDTO userDataModel) {
		return userService.updateUserProfile(userDataModel);
	}

	@Operation(summary = "find existing user record by username", description = "Retrieves existing user record in the system by username",
			tags = {"User"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User record successfully retrieved", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "User does not exist", content = @Content) })
	@GetMapping("/user")
	public User findUserByDisplayName(@RequestParam("name") Optional<String> name) {
		UserSearchDTO userSearchModel = new UserSearchDTO();
		userSearchModel.setName(name);
		return userService.findUserByDisplayName(userSearchModel);
	}
}
