package com.example.DiningReviewApi.DiningReviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DiningReviewApi.DataModels.DiningReviewDTO;
import com.example.DiningReviewApi.DataModels.RestaurantSearchDTO;
import com.example.DiningReviewApi.DataModels.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DiningReviewController {

	@Autowired
	DiningReviewService diningReviewService;

	@Operation(summary = "Creates a new diningReview record into the system", description = "Creates a new diningReview record into the system", tags = {
			"DiningReview" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "diningReview submitted successfully ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Default error sample response", content = @Content) })
	@PostMapping("/diningReview")
	public DiningReview submitDiningReview(@RequestBody DiningReviewDTO diningReviewDataModel) {
		return diningReviewService.submitDiningReview(diningReviewDataModel);
	}

	@Operation(summary = "Retrieve all pending diningReview records from the system", description = "List all current submitted restaurants reviews but pending in the system", tags = {
			"DiningReview" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieve all pending diningReview records from the system"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@GetMapping("/diningReview/pending")
	public List<DiningReview> getAllPendingDiningReviews() {
		return diningReviewService.getAllPendingDiningReviews();
	}

	@Operation(summary = "Approve/Reject diningReview records submitted by users", description = "Approve/Reject diningReview records submitted by users", tags = {
			"Admin" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Approve/Reject diningReview records submitted by users"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@PatchMapping("/diningReviews/{restaurantId}")
	public DiningReview approveAndRejectAGivenDiningReview(@RequestParam("status") Status status,
			@RequestParam("reviewId") Long reviewId, @PathVariable("restaurantId") Long restaurantId) {
		RestaurantSearchDTO restaurantSearchModel = new RestaurantSearchDTO();
		restaurantSearchModel.setId(restaurantId);
		restaurantSearchModel.setReviewId(reviewId);
		return diningReviewService.approveAndRejectAGivenDiningReview(restaurantSearchModel, status);
	}

	@Operation(summary = "Retrieve all approved diningReview records for a given Restaurant from the system", description = "List all current submitted restaurants reviews and approved in the system foir a given restaurant", tags = {
			"DiningReview" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieve all approved diningReview records from the system for a given restaurant"),
			@ApiResponse(responseCode = "400", description = "Default error sample response") })
	@GetMapping("/diningReview/all/approved")
	public List<DiningReview> retrieveAllApprovedReviewsforAGivenRestaurant(
			@RequestParam("restaurantId") Long restaurantId) {
		RestaurantSearchDTO restaurantSearchModel = new RestaurantSearchDTO();
		restaurantSearchModel.setId(restaurantId);
		return diningReviewService.retrieveAllApprovedReviewsforAGivenRestaurant(restaurantSearchModel);
	}

}
