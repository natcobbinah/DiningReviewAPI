package com.example.DiningReviewApi.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.DiningReviewApi.DataModels.UserDataModel;
import com.example.DiningReviewApi.DataModels.UserSearchModel;
import com.example.DiningReviewApi.Validators.NameValidator;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User createUser(UserDataModel userDataModel){

		UserAddress userAddress = new UserAddress();
		userAddress.setCity(userDataModel.getUserAddressDataModel().getCity());
		userAddress.setZipCode(userDataModel.getUserAddressDataModel().getZipCode());

		User user = new User();
		user.setDisplayName(userDataModel.getDisplayName());
		user.setUserAddress(userAddress);
		user.setInterestPeanutAllergies(userDataModel.isInterestDiaryAllergies());
		user.setInterestEggAllergies(userDataModel.isInterestEggAllergies());
		user.setInterestDiaryAllergies(userDataModel.isInterestDiaryAllergies());

		// verify from DB if unique userName does not exists b4 creating user
		Optional<User> OptionalfindIfUserExists = userRepository.findByDisplayName(userDataModel.getDisplayName());

		if (OptionalfindIfUserExists.isPresent()) {
			User alreadyExistingUser = OptionalfindIfUserExists.get();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User with " + alreadyExistingUser.getDisplayName() + " Already Exists");
		} else {
			userRepository.save(user);
		}
		return user;
	}

	public User updateUserProfile(UserDataModel userDataModel) {
		// As a registered User can update my profile but not displayName
		Optional<User> fetchUserRecordToUpdate = userRepository.findByDisplayName(userDataModel.getDisplayName());

		if (fetchUserRecordToUpdate.isPresent()) {
			User userRecordToUpdate = fetchUserRecordToUpdate.get();

			UserAddress userAddress = new UserAddress();
			userAddress.setCity(userDataModel.getUserAddressDataModel().getCity());
			userAddress.setZipCode(userDataModel.getUserAddressDataModel().getZipCode());

			userRecordToUpdate.setInterestPeanutAllergies(userDataModel.isInterestPeanutAllergies());
			userRecordToUpdate.setInterestEggAllergies(userDataModel.isInterestEggAllergies());
			userRecordToUpdate.setInterestDiaryAllergies(userDataModel.isInterestDiaryAllergies());
			userRecordToUpdate.setUserAddress(userAddress);

			userRepository.save(userRecordToUpdate);
			return userRecordToUpdate;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"User with " + userDataModel.getDisplayName() + " does Not Exists");
		}
	}

	public User findUserByDisplayName(UserSearchModel userSearchModel)  {

		String validatedName = NameValidator.ValidateName(userSearchModel.getName().get());

		if (validatedName.isBlank()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User parameters passed");
		}

		Optional<User> retrieveUserProfile = userRepository.findByDisplayName(validatedName);

		if (retrieveUserProfile.isPresent()) {
			User userInfo = retrieveUserProfile.get();
			return userInfo;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found with the given Username");
		}
	}

}
