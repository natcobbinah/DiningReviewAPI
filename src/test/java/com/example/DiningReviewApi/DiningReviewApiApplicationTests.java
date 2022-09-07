package com.example.DiningReviewApi;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DiningReviewApi.DiningReviews.DiningReview;
import com.example.DiningReviewApi.DiningReviews.DiningReviewRepository;
import com.example.DiningReviewApi.DiningReviews.ReviewScore;
import com.example.DiningReviewApi.DiningReviews.Status;
import com.example.DiningReviewApi.Restaurant.Restaurant;
import com.example.DiningReviewApi.Restaurant.RestaurantAddress;
import com.example.DiningReviewApi.Restaurant.RestaurantRepository;
import com.example.DiningReviewApi.User.User;
import com.example.DiningReviewApi.User.UserAddress;
import com.example.DiningReviewApi.User.UserRepository;

@SpringBootTest
class DiningReviewApiApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DiningReviewRepository diningReviewRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	//on Users
	@Test
	public void createUser() {
		//As an unregistered user create my userProfile with  a unique user displayName

		UserAddress userAddress = new UserAddress();
		userAddress.setCity("Montigny-Le-Bretonneux");
		userAddress.setZipCode("78180");

		User user = new User();
		user.setDisplayName("Kamikaze");
		user.setUserAddress(userAddress);
		user.setInterestPeanutAllergies(false);
		user.setInterestEggAllergies(false);
		user.setInterestDiaryAllergies(false);

		// verify from DB if unique userName does not exists b4
		// creating user
		Optional<User> OptionalfindIfUserExists = userRepository.findByDisplayName(user.getDisplayName());

		if (OptionalfindIfUserExists.isPresent()) {
			User alreadyExistingUser = OptionalfindIfUserExists.get();
			System.out.println("UserName:" + alreadyExistingUser.getDisplayName() + "Already Exists");
		} else {
			userRepository.save(user);
			System.out.println("New User created Successfully");
		}

	}
	
	@Test
	public void updateUserProfile() {
		//As a registered User can update my profile but not displayName
		Optional<User> fetchUserRecordsToUpdate = userRepository.findByDisplayName("Kamikaze");
		
		if(fetchUserRecordsToUpdate.isPresent()) {
			User userRecordsToUpdate = fetchUserRecordsToUpdate.get();
			
			UserAddress userAddress = new UserAddress();
			userAddress.setCity("Coubervoire");
			userAddress.setZipCode("75150");
			
			userRecordsToUpdate.setInterestPeanutAllergies(true);
			userRecordsToUpdate.setInterestEggAllergies(false);
			userRecordsToUpdate.setInterestDiaryAllergies(true);
			userRecordsToUpdate.setUserAddress(userAddress);
			
			userRepository.save(userRecordsToUpdate);
			System.out.println(userRecordsToUpdate);
		}else {
			System.out.println("User record does not exist");
		}
	}
	
	@Test
	public void findUserByDisplayName() {
		Optional<User> retrieveUserProfile = userRepository.findByDisplayName("Kamikaze");
		
		if(retrieveUserProfile.isPresent()) {
			User userInfo = retrieveUserProfile.get();
			System.out.println(userInfo);
		}else {
			System.out.println("No User Record Found");
		}
	}
	
	//on Restaurants
	@Test
	public void createRestaurant() {
		//submit new restaurant entry as user
		//if restaurant with sameName and zipCode exists throw error
		
		RestaurantAddress restaurantAddress = new RestaurantAddress();
		restaurantAddress.setName("Mercure");
		restaurantAddress.setZipCode("78180");
		
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantAddress(restaurantAddress);
		restaurant.setPeanutAllergyScore(0);
		restaurant.setEggAllergyScore(0);
		restaurant.setDairyAllergyScore(0);
		restaurant.setOverAllRestaurantScore(0);
		
		Optional<Restaurant> retrieveRestaurantIfAlreadyExists = restaurantRepository.findByRestaurantAddress(restaurantAddress);
		
		if(retrieveRestaurantIfAlreadyExists.isPresent()) {
			System.out.println("The given restaurant already exists. Cannot created restaurant Entry");
		}else {
			restaurantRepository.save(restaurant);
			System.out.println("Restaurant entry created successfully");
		}
	}
	
	@Test
	public void fetchRestaurantDetailsById() {
		//fetch details of a restaurant given its uniqueId
		
		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(1L);
		
		if(!fetchRestaurantById.isPresent()) {
			System.out.println("Restaurant with the givenId does not exist");
		}else {
			Restaurant restaurant = fetchRestaurantById.get();
			System.out.println(restaurant);
		}
	}
	
	@Test
	public void fetchRestaurantsByZipCodeWithReviews() {
		//fetch restaurants by ZipCode with at least one user submitted Review
		Optional<Restaurant> fetchRestaurantsByZipCode = restaurantRepository.findByRestaurantAddressZipCode("78180");
		
		if(!fetchRestaurantsByZipCode.isPresent()) {
			System.out.println("No restaurants exists in the given zipCode search");
		}else {
			Restaurant restaurant = fetchRestaurantsByZipCode.get();
			
			//now verify if restaurant has any reviews
			if(restaurant.getDiningReview().size() > 0) {
				System.out.println(restaurant);
			}else {
				System.out.println("Restaurants exists in the given ZipCode but no reviews are available");
			}
		}
	}

}
