package com.example.DiningReviewApi.Restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DiningReviewApi.DataModels.RestaurantDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;
import com.example.DiningReviewApi.ExceptionHandlers.AlreadyExistException;
import com.example.DiningReviewApi.ExceptionHandlers.NotFoundException;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	public Restaurant createRestaurant(RestaurantDataModel restaurantDataModel) {
		// submit new restaurant entry as user
		// if restaurant with sameName and zipCode exists throw AlreadyExistException

		RestaurantAddress restaurantAddress = new RestaurantAddress();
		restaurantAddress.setName(restaurantDataModel.getRestaurantAddress().getName());
		restaurantAddress.setZipCode(restaurantDataModel.getRestaurantAddress().getZipCode());

		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantAddress(restaurantAddress);
		restaurant.setPeanutAllergyScore(restaurantDataModel.getEggAllergyScore());
		restaurant.setEggAllergyScore(restaurantDataModel.getEggAllergyScore());
		restaurant.setDairyAllergyScore(restaurantDataModel.getDairyAllergyScore());
		restaurant.setOverAllRestaurantScore(restaurantDataModel.getOverAllRestaurantScore());

		Optional<Restaurant> retrieveRestaurantIfAlreadyExists = restaurantRepository
				.findByRestaurantAddress(restaurantAddress);

		if (retrieveRestaurantIfAlreadyExists.isPresent()) {
			throw new AlreadyExistException("The given restaurant already exists. Cannot created restaurant Entry");
		} else {
			restaurantRepository.save(restaurant);
			return restaurant;
		}
	}

	public Restaurant fetchRestaurantDetailsById(RestaurantSearchModel restaurantSearchModel) {
		// fetch details of a restaurant given its uniqueId

		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new NotFoundException("Restaurant with the givenId does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();
			return restaurant;
		}
	}

	public List<Restaurant> fetchRestaurantsByZipCodeWithReviews(RestaurantSearchModel restaurantSearchModel) {
		// fetch restaurants by ZipCode with at least one user submitted Review		
		Optional<List<Restaurant>> fetchRestaurantsByZipCode = restaurantRepository
				.findByRestaurantAddressZipCodeOrderByRestaurantAddressNameDesc(restaurantSearchModel.getZipCode().get());

		List<Restaurant> restaurants = fetchRestaurantsByZipCode.get();
		
		List<Restaurant> restaurantsWithReviews = new LinkedList<>();

		// now verify if restaurant has any reviews
		restaurants.forEach(restaurant -> {
			if (restaurant.getDiningReview().size() > 0) {
				System.out.println(restaurant);
				restaurantsWithReviews.add(restaurant);
			}
		});
		
		return restaurantsWithReviews;
	}
}
