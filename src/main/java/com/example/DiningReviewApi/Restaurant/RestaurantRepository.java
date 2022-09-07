package com.example.DiningReviewApi.Restaurant;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	Optional<Restaurant> findByRestaurantAddress(RestaurantAddress restaurantAddress);
	
	Optional<Restaurant> findByRestaurantAddressZipCode(String zipCode);
}
