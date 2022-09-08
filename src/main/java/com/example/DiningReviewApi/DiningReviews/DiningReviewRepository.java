package com.example.DiningReviewApi.DiningReviews;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.DiningReviewApi.Restaurant.Restaurant;

@Repository
public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
	
	List<DiningReview> getAllByStatusEquals(Status statusValue);
	
	List<DiningReview> getAllByRestaurantAndStatusEquals(Restaurant restaurant, Status statusValue);
}
