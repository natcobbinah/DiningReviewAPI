package com.example.DiningReviewApi.DiningReviews;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.DiningReviewApi.Restaurant.Restaurant;

@Repository
public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
	
	List<DiningReview> getAllByStatusEquals(Status statusValue);
	
	List<DiningReview> getAllByRestaurantAndStatusEquals(Restaurant restaurant, Status statusValue);
	
	List<DiningReview> getByRestaurantAndStatusEquals(Restaurant restaurant, Status statusValue);
	
	@Query(value = "SELECT AVG(d.dairyAllergyScore) from DiningReview d where d.restaurant.restaurant_Id = :restaurant_Id")
	Double averageForDiaryAllergy(Long restaurant_Id);
	
	@Query(value="SELECT AVG(d.eggAllergyScore) from DiningReview d where d.restaurant.restaurant_Id = :restaurant_Id")
	Double averageForEggAllergy(Long restaurant_Id);
	
	@Query(value="SELECT AVG(d.peanutAllergyScore) from DiningReview d where d.restaurant.restaurant_Id = :restaurant_Id")
	Double averageForPeanutAllergy(Long restaurant_Id);
}
