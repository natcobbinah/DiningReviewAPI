package com.example.DiningReviewApi.Restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	Optional<Restaurant> findByRestaurantAddress(RestaurantAddress restaurantAddress);
	
	Optional<List<Restaurant>> findByRestaurantAddressZipCodeOrderByRestaurantAddressNameDesc(String zipCode);
}
