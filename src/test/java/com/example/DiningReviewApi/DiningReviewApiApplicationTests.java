package com.example.DiningReviewApi;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DiningReviewApi.DataModels.AdminReview;
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

//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	DiningReviewRepository diningReviewRepository;
//
//	@Autowired
//	RestaurantRepository restaurantRepository;
//
//	// on Users
//	@Test
//	public void createUser() {
//		// As an unregistered user create my userProfile with a unique user displayName
//
//		UserAddress userAddress = new UserAddress();
//		userAddress.setCity("Montigny-Le-Bretonneux");
//		userAddress.setZipCode("78180");
//
//		User user = new User();
//		user.setDisplayName("Kamikaze");
//		user.setUserAddress(userAddress);
//		user.setInterestPeanutAllergies(false);
//		user.setInterestEggAllergies(false);
//		user.setInterestDiaryAllergies(false);
//
//		// verify from DB if unique userName does not exists b4
//		// creating user
//		Optional<User> OptionalfindIfUserExists = userRepository.findByDisplayName(user.getDisplayName());
//
//		if (OptionalfindIfUserExists.isPresent()) {
//			User alreadyExistingUser = OptionalfindIfUserExists.get();
//			System.out.println("UserName:" + alreadyExistingUser.getDisplayName() + "Already Exists");
//		} else {
//			userRepository.save(user);
//			System.out.println("New User created Successfully");
//		}
//
//	}
//
//	@Test
//	public void updateUserProfile() {
//		// As a registered User can update my profile but not displayName
//		Optional<User> fetchUserRecordsToUpdate = userRepository.findByDisplayName("Kamikaze");
//
//		if (fetchUserRecordsToUpdate.isPresent()) {
//			User userRecordsToUpdate = fetchUserRecordsToUpdate.get();
//
//			UserAddress userAddress = new UserAddress();
//			userAddress.setCity("Coubervoire");
//			userAddress.setZipCode("75150");
//
//			userRecordsToUpdate.setInterestPeanutAllergies(true);
//			userRecordsToUpdate.setInterestEggAllergies(false);
//			userRecordsToUpdate.setInterestDiaryAllergies(true);
//			userRecordsToUpdate.setUserAddress(userAddress);
//
//			userRepository.save(userRecordsToUpdate);
//			System.out.println(userRecordsToUpdate);
//		} else {
//			System.out.println("User record does not exist");
//		}
//	}
//
//	@Test
//	public void findUserByDisplayName() {
//		Optional<User> retrieveUserProfile = userRepository.findByDisplayName("Kamikaze");
//
//		if (retrieveUserProfile.isPresent()) {
//			User userInfo = retrieveUserProfile.get();
//			System.out.println(userInfo);
//		} else {
//			System.out.println("No User Record Found");
//		}
//	}
//
//	// on Restaurants
//	@Test
//	public void createRestaurant() {
//		// submit new restaurant entry as user
//		// if restaurant with sameName and zipCode exists throw error
//		
//		//When creating a new Restaurant we, set only the address, as the other fields
//		//will be set to null
//		//It will then be updated as user submit reviews on the restaurant
//
//		RestaurantAddress restaurantAddress = new RestaurantAddress();
//		restaurantAddress.setName("Mercure");
//		restaurantAddress.setZipCode("78180");
//
//		Restaurant restaurant = new Restaurant();
//		restaurant.setRestaurantAddress(restaurantAddress);
////		restaurant.setPeanutAllergyScore(0);
////		restaurant.setEggAllergyScore(0);
////		restaurant.setDairyAllergyScore(0);
////		restaurant.setOverAllRestaurantScore(0);
//
//		Optional<Restaurant> retrieveRestaurantIfAlreadyExists = restaurantRepository
//				.findByRestaurantAddress(restaurantAddress);
//
//		if (retrieveRestaurantIfAlreadyExists.isPresent()) {
//			System.out.println("The given restaurant already exists. Cannot created restaurant Entry");
//		} else {
//			restaurantRepository.save(restaurant);
//			System.out.println("Restaurant entry created successfully");
//		}
//	}
//
//	@Test
//	public void fetchRestaurantDetailsById() {
//		// fetch details of a restaurant given its uniqueId
//
//		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(1L);
//
//		if (!fetchRestaurantById.isPresent()) {
//			System.out.println("Restaurant with the givenId does not exist");
//		} else {
//			Restaurant restaurant = fetchRestaurantById.get();
//			System.out.println(restaurant);
//		}
//	}
//	
//	@Test
//	public void fetchAllRestaurants() {
//		Iterable<Restaurant> allRestaurants = restaurantRepository.findAll();
//		allRestaurants.forEach(restaurant -> {
//			System.out.println(restaurant);
//		});
//	}
//
//	@Test
//	public void fetchRestaurantsByZipCodeWithReviews() {
//		// fetch restaurants by ZipCode with at least one user submitted Review
//		Optional<List<Restaurant>> fetchRestaurantsByZipCode = restaurantRepository
//				.findByRestaurantAddressZipCodeOrderByRestaurantAddressNameDesc("78180");
//
//		List<Restaurant> restaurants = fetchRestaurantsByZipCode.get();
//
//		// now verify if restaurant has any reviews
//		restaurants.forEach(restaurant -> {
//			if (restaurant.getDiningReview().size() > 0) {
//				System.out.println(restaurant);
//			}
//		});
//	}
//
//	// on diningReviews
//	@Test
//	public void submitDiningReview() {
//		// a registeredUser should be able to give a diningReview
//		// select restaurant to give review either by (ID/ADDRESS/ZipCode)
//
//		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(3L);
//
//		if (!fetchRestaurantById.isPresent()) {
//			System.out.println("Restaurant with the givenId does not exist");
//		} else {
//			Restaurant restaurant = fetchRestaurantById.get();
//
//			DiningReview diningReview = new DiningReview();
//			diningReview.setReviewerName("Balber");
//			diningReview.setRestaurant(restaurant);
//			diningReview.setPeanutAllergyScore(ReviewScore.FOUR);
//			diningReview.setEggAllergyScore(ReviewScore.THREE);
//			diningReview.setDairyAllergyScore(ReviewScore.FIVE);
//			diningReview.setStatus(Status.REJECTED);
//			diningReview.setCommentary("Restaurant pays attention to allergic food  for its users");
//
//			diningReviewRepository.save(diningReview);
//			System.out.println("Dining review by user saved successfully");
//
//		}
//	}
//
//	@Test
//	public void getAllPendingDiningReviews() {
//		// An admin should be able to get list of all dining reviews pending approval
//
//		List<DiningReview> diningReviewsPendingApprovalList = diningReviewRepository
//				.getAllByStatusEquals(Status.PENDING);
//		diningReviewsPendingApprovalList.forEach(review -> {
//			System.out.println(review);
//		});
//	}
//
//	@Test
//	public void approveAndRejectAGivenDiningReview() {
//		// An admin should be able to approve/reject a given dining Review
//		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(3L);
//
//		if (!fetchRestaurantById.isPresent()) {
//			System.out.println("Given restaurant does not exist");
//		}
//
//		Restaurant restaurant = fetchRestaurantById.get();
//
//		List<DiningReview> reviewToApproveOrReject = diningReviewRepository.getByRestaurantAndStatusEquals(restaurant,
//				Status.PENDING);
//
//		Optional<DiningReview> diningReviewById = diningReviewRepository.findById(7L);
//
//		if (!diningReviewById.isPresent()) {
//			System.out.println("Not diningReview has the assigned Id");
//		}
//		DiningReview foundreviewById = diningReviewById.get();
//
//		for (DiningReview diningReview : reviewToApproveOrReject) {
//			System.out.println("diningReview =  [ " + diningReview);
//			System.out.println("foundreviewById =  [ " + foundreviewById);
//
//			if (diningReview.getDiningReviewId() == foundreviewById.getDiningReviewId()) {
//				// call AdminReview Operation on diningReview Status here DiningReview
//				foundreviewById = AdminReview.acceptOrRejectDiningReviewStatusByUser(diningReview, Status.ACCEPTED);
//				diningReviewRepository.save(foundreviewById);
//				System.out.println("Status updated successfully");
//				break;
//			} else {
//				System.out.println("No reviewId matched the provided diningReviewId");
//			}
//		}
//	}
//
//	@Test
//	public void findByDiningReviewId() {
//		Optional<DiningReview> diningReview = diningReviewRepository.findById(7L);
//
//		if (!diningReview.isPresent()) {
//			System.out.println("No diningReview found");
//		} else {
//			DiningReview reviewFound = diningReview.get();
//			System.out.println(reviewFound);
//		}
//	}
//
//	@Test
//	public void retrieveAllApprovedReviewsforAGivenRestaurant() {
//
//		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(3L);
//
//		if (!fetchRestaurantById.isPresent()) {
//			System.out.println("Given restaurant does not exist");
//		} else {
//			Restaurant restaurant = fetchRestaurantById.get();
//
//			List<DiningReview> allApprovedReviewsforGivenRestaurant = diningReviewRepository
//					.getAllByRestaurantAndStatusEquals(restaurant, Status.ACCEPTED);
//
//			allApprovedReviewsforGivenRestaurant.forEach(diningReview -> {
//				System.out.println(diningReview);
//			});
//		}
//	}
//	
//	//computation aspect
//	@Test
//	public void getAverageforDiaryAllergyReviewScores() {
//		Double averageScore = diningReviewRepository.averageForDiaryAllergy(3L);
//		System.out.println(averageScore);
//	}
//	
//	@Test
//	public void getAverageforEggAllergyReviewScores() {
//		Double averageScore = diningReviewRepository.averageForEggAllergy(3L);
//		System.out.println(averageScore);
//	}
//	
//	@Test
//	public void getAverageforPeanutAllergyReviewScores() {
//		Double averageScore = diningReviewRepository.averageForPeanutAllergy(3L);
//		System.out.println(averageScore);
//	}

}
