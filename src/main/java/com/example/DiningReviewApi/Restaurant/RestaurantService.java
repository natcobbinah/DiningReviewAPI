package com.example.DiningReviewApi.Restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DiningReviewApi.DataModels.RestaurantDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	public Restaurant createRestaurant(RestaurantDataModel restaurantDataModel) throws Exception {
		// submit new restaurant entry as user
		// if restaurant with sameName and zipCode exists throw error

		RestaurantAddress restaurantAddress = new RestaurantAddress();
		restaurantAddress.setName(restaurantDataModel.getRestaurantAddressDataModel().getName());
		restaurantAddress.setZipCode(restaurantDataModel.getRestaurantAddressDataModel().getZipCode());

		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantAddress(restaurantAddress);
		restaurant.setPeanutAllergyScore(restaurantDataModel.getPeanutAllergyScore());
		restaurant.setEggAllergyScore(restaurantDataModel.getEggAllergyScore());
		restaurant.setDairyAllergyScore(restaurantDataModel.getDairyAllergyScore());
		restaurant.setOverAllRestaurantScore(restaurantDataModel.getOverAllRestaurantScore());

		Optional<Restaurant> retrieveRestaurantIfAlreadyExists = restaurantRepository
				.findByRestaurantAddress(restaurantAddress);

		if (retrieveRestaurantIfAlreadyExists.isPresent()) {
			throw new Exception("The given restaurant already exists. Cannot created restaurant Entry");
		} else {
			restaurantRepository.save(restaurant);
			return restaurant;
		}
	}

	public Restaurant fetchRestaurantDetailsById(RestaurantSearchModel restaurantSearchModel) throws Exception {
		// fetch details of a restaurant given its uniqueId

		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new Exception("Restaurant with the givenId does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();
			return restaurant;
		}
	}

	public List<Restaurant> fetchRestaurantsByZipCodeWithReviews(RestaurantSearchModel restaurantSearchModel)
			throws Exception {
		// fetch restaurants by ZipCode with at least one user submitted Review
		Optional<List<Restaurant>> fetchRestaurantsByZipCode = restaurantRepository
				.findByRestaurantAddressZipCodeOrderByRestaurantAddressNameDesc(restaurantSearchModel.getZipCode());

		if (!fetchRestaurantsByZipCode.isPresent()) {
			throw new Exception("No restaurants exists in the given zipCode search");
		} else {
			List<Restaurant> restaurant = fetchRestaurantsByZipCode.get();

			List<Restaurant> appendedRestaurantWithReviews = new LinkedList<>();

			// now verify if restaurant has any reviews
			restaurant.forEach(subRestaurant -> {
				if (subRestaurant.getDiningReview().size() > 0) {
					appendedRestaurantWithReviews.add(subRestaurant);
				} else {
					System.out.println(restaurant.size()
							+ " Restaurants exists in the given ZipCode but no reviews are available");
				}
			});
			return appendedRestaurantWithReviews;
		}
	}
}
