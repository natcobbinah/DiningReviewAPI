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

import com.example.DiningReviewApi.DataModels.UserDataModel;
import com.example.DiningReviewApi.DataModels.UserSearchModel;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "User created Successfully")
	public User createUser(@RequestBody UserDataModel userDataModel) {
		return userService.createUser(userDataModel);
	}

	@PutMapping("/user")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "User updated Successfully")
	public User updateUserProfile(@RequestBody UserDataModel userDataModel) {
		return userService.updateUserProfile(userDataModel);
	}

	@GetMapping("/user")
	public User findUserByDisplayName(@RequestParam("name") Optional<String> name) {
		UserSearchModel userSearchModel = new UserSearchModel();
		userSearchModel.setName(name);
		return userService.findUserByDisplayName(userSearchModel);
	}
}
