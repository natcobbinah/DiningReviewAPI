package com.example.DiningReviewApi.User;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DiningReviewApi.DataModels.UserDTO;
import com.example.DiningReviewApi.DataModels.UserSearchDTO;
import com.example.DiningReviewApi.ExceptionHandlers.AlreadyExistException;
import com.example.DiningReviewApi.ExceptionHandlers.NotFoundException;
import com.example.DiningReviewApi.Validators.NameValidator;

import org.apache.commons.lang3.StringUtils;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User createUser(UserDTO userDataModel) {

		boolean validatedName = NameValidator.isNameValid(userDataModel.getDisplayName());

		if (!validatedName) {
			throw new NotFoundException("Invalid characters in userName");
		}

		UserAddress userAddress = new UserAddress();
		userAddress.setCity(userDataModel.getUserAddress().getCity());
		userAddress.setZipCode(userDataModel.getUserAddress().getZipCode());

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
			throw new AlreadyExistException("User with " + alreadyExistingUser.getDisplayName() + " Already Exists");
		} else {
			userRepository.save(user);
		}
		return user;
	}

	public User updateUserProfile(UserDTO userDataModel) {
		// As a registered User can update my profile but not displayName
		Optional<User> fetchUserRecordToUpdate = userRepository.findByDisplayName(userDataModel.getDisplayName());

		if (fetchUserRecordToUpdate.isPresent()) {
			User userRecordToUpdate = fetchUserRecordToUpdate.get();

			UserAddress userAddress = new UserAddress();
			userAddress.setCity(userDataModel.getUserAddress().getCity());
			userAddress.setZipCode(userDataModel.getUserAddress().getZipCode());

			userRecordToUpdate.setInterestPeanutAllergies(userDataModel.isInterestPeanutAllergies());
			userRecordToUpdate.setInterestEggAllergies(userDataModel.isInterestEggAllergies());
			userRecordToUpdate.setInterestDiaryAllergies(userDataModel.isInterestDiaryAllergies());
			userRecordToUpdate.setUserAddress(userAddress);

			userRepository.save(userRecordToUpdate);
			return userRecordToUpdate;
		} else {
			throw new NotFoundException("User with " + userDataModel.getDisplayName() + " does Not Exists");
		}
	}

	public User findUserByDisplayName(UserSearchDTO userSearchModel) {

		String validatedName = NameValidator.ValidateName(userSearchModel.getName().get());

		if ( StringUtils.isBlank(validatedName)) {
			throw new NotFoundException("Invalid User parameters passed");
		}

		Optional<User> retrieveUserProfile = userRepository.findByDisplayName(validatedName);

		if (retrieveUserProfile.isPresent()) {
			User userInfo = retrieveUserProfile.get();
			return userInfo;
		} else {
			throw new NotFoundException("No User found with the given Username");
		}
	}

}
