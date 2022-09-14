package com.example.DiningReviewApi.DataModels;

import java.util.Optional;

import lombok.Data;

@Data
public class RestaurantSearchModel {
	
	private Long id;
	
	private Long reviewId;
	
	private Optional<String> zipCode;

}
