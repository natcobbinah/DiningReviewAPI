package com.example.DiningReviewApi.Restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DiningReviewApi.DataModels.RestaurantDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;
import com.example.DiningReviewApi.ExceptionHandlers.NotFoundException;

@RestController
@RequestMapping(value = "/api/v1/")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@PostMapping("/restaurant")
	public Restaurant createRestaurant(@RequestBody RestaurantDataModel restaurantDataModel) {
		return restaurantService.createRestaurant(restaurantDataModel);
	}

	@GetMapping("/restaurant/{id}")
	public Restaurant fetchRestaurantDetailsById(@PathVariable("id") Long id) {
		RestaurantSearchModel restaurantSearchModel = new RestaurantSearchModel();
		restaurantSearchModel.setId(id);
		return restaurantService.fetchRestaurantDetailsById(restaurantSearchModel);
	}

	@GetMapping("/restaurant")
	public List<Restaurant> fetchRestaurantsByZipCodeWithReviews(@RequestParam("zipCode") Optional<String> zipCode) {
		RestaurantSearchModel restaurantSearchModel = new RestaurantSearchModel();
		restaurantSearchModel.setZipCode(zipCode);
		return restaurantService.fetchRestaurantsByZipCodeWithReviews(restaurantSearchModel);
	}
}
