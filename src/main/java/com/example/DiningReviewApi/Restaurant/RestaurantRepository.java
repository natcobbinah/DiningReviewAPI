package com.example.DiningReviewApi.Restaurant;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	//@Query(value = "SELECT r.restaurantAddress.name, r.restaurantAddress.zipCode from Restaurant r where r.name = ?1 and r.zipCode = ?1")
	Optional<Restaurant> findByRestaurantAddress(RestaurantAddress restaurantAddress);
}
