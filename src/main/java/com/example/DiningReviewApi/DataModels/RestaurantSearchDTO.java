package com.example.DiningReviewApi.DataModels;

import java.util.Optional;

import lombok.Data;

@Data
public class RestaurantSearchDTO {
	
	private Long id;
	
	private Long reviewId;
	
	private Optional<String> zipCode;

}
