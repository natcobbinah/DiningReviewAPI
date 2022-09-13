package com.example.DiningReviewApi.DiningReviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DiningReviewApi.DataModels.DiningReviewDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;

@RestController
@RequestMapping(value = "/api/v1/")
public class DiningReviewController {

	@Autowired
	DiningReviewService diningReviewService;

	@PostMapping("/diningReview")
	public DiningReview submitDiningReview(@RequestBody DiningReviewDataModel diningReviewDataModel) {
		return diningReviewService.submitDiningReview(diningReviewDataModel);
	}

	@GetMapping("/diningReview/pending")
	public List<DiningReview> getAllPendingDiningReviews() {
		return diningReviewService.getAllPendingDiningReviews();
	}

	@PatchMapping("/diningReviews/{restaurantId}")
	public DiningReview approveAndRejectAGivenDiningReview(@RequestParam("status") Status status,
			@PathVariable("restaurantId") Long restaurantId) {
		RestaurantSearchModel restaurantSearchModel = new RestaurantSearchModel();
		restaurantSearchModel.setId(restaurantId);
		return diningReviewService.approveAndRejectAGivenDiningReview(restaurantSearchModel, status);
	}

	@GetMapping("/diningReview/all/approved")
	public List<DiningReview> retrieveAllApprovedReviewsforAGivenRestaurant(
			@RequestParam("restaurantId") Long restaurantId) {
		RestaurantSearchModel restaurantSearchModel = new RestaurantSearchModel();
		restaurantSearchModel.setId(restaurantId);
		return diningReviewService.retrieveAllApprovedReviewsforAGivenRestaurant(restaurantSearchModel);
	}

}
