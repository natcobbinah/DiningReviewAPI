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

import com.example.DiningReviewApi.DataModels.RestaurantDTO;
import com.example.DiningReviewApi.DataModels.RestaurantSearchDTO;
import com.example.DiningReviewApi.DataModels.UserDTO;
import com.example.DiningReviewApi.ExceptionHandlers.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@Operation(summary = "Creates a new restaurant record into the system", description = "Registers a new restaurant into the system", tags = {
			"Restaurant" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Restaurant  successfully created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "An existing restaurant with same name and address already exist", content = @Content) })
	@PostMapping("/restaurant")
	public Restaurant createRestaurant(@RequestBody RestaurantDTO restaurantDataModel) {
		return restaurantService.createRestaurant(restaurantDataModel);
	}

	@Operation(summary = "Retrieve all restaurant records from the system", description = "List all current registered restaurants in the system", tags = {
			"Restaurant" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetches all restaurant records from the system"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@GetMapping("/restaurant")
	public Iterable<Restaurant> fetchAllRestaurants() {
		return restaurantService.fetchAllRestaurants();
	}

	@Operation(summary = "Retrieve  restaurant records  from the system by its UniqueID", tags = { "Restaurant" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetches  restaurant records from the system by its uniqueID"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@GetMapping("/restaurant/{id}")
	public Restaurant fetchRestaurantDetailsById(@PathVariable("id") Long id) {
		RestaurantSearchDTO restaurantSearchModel = new RestaurantSearchDTO();
		restaurantSearchModel.setId(id);
		return restaurantService.fetchRestaurantDetailsById(restaurantSearchModel);
	}

	@Operation(summary = "Retrieve  restaurant  record by zipCode with atleast one user submitted review", tags = {
			"Restaurant" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetches  restaurant  record by zipCode with atleast one user submitted review"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@GetMapping("/restaurant/reviews")
	public List<Restaurant> fetchRestaurantsByZipCodeWithReviews(@RequestParam("zipCode") Optional<String> zipCode) {
		RestaurantSearchDTO restaurantSearchModel = new RestaurantSearchDTO();
		restaurantSearchModel.setZipCode(zipCode);
		return restaurantService.fetchRestaurantsByZipCodeWithReviews(restaurantSearchModel);
	}
}
