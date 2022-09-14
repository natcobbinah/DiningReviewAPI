package com.example.DiningReviewApi.DiningReviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DiningReviewApi.DataModels.AdminReview;
import com.example.DiningReviewApi.DataModels.DiningReviewDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;
import com.example.DiningReviewApi.ExceptionHandlers.NotFoundException;
import com.example.DiningReviewApi.Restaurant.Restaurant;
import com.example.DiningReviewApi.Restaurant.RestaurantRepository;
import com.example.DiningReviewApi.Validators.NameValidator;

@Service
public class DiningReviewService {

	@Autowired
	DiningReviewRepository diningReviewRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	public DiningReview submitDiningReview(DiningReviewDataModel diningReviewDataModel) {
		// a registeredUser should be able to give a diningReview
		// select restaurant to give review either by (ID/ADDRESS/ZipCode)

		Optional<Restaurant> fetchRestaurantById = restaurantRepository
				.findById(diningReviewDataModel.getRestaurantId());

		if (!fetchRestaurantById.isPresent()) {
			throw new NotFoundException("Restaurant with the givenId does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();

			boolean validatedReviewerName = NameValidator.isNameValid(diningReviewDataModel.getReviewerName());

			if (!validatedReviewerName) {
				throw new NotFoundException("Invalid characters in reviewerName");
			}

			DiningReview diningReview = new DiningReview();
			diningReview.setReviewerName(diningReviewDataModel.getReviewerName());
			diningReview.setRestaurant(restaurant);
			diningReview.setPeanutAllergyScore(diningReviewDataModel.getPeanutAllergyScore());
			diningReview.setEggAllergyScore(diningReviewDataModel.getEggAllergyScore());
			diningReview.setDairyAllergyScore(diningReviewDataModel.getDairyAllergyScore());
			diningReview.setStatus(Status.PENDING);
			diningReview.setCommentary(diningReviewDataModel.getCommentary());

			diningReviewRepository.save(diningReview);
			return diningReview;
		}
	}

	public List<DiningReview> getAllPendingDiningReviews() {
		// An admin should be able to get list of all dining reviews pending approval
		List<DiningReview> diningReviewsPendingApprovalList = diningReviewRepository
				.getAllByStatusEquals(Status.PENDING);

		return diningReviewsPendingApprovalList;
	}

	public DiningReview approveAndRejectAGivenDiningReview(RestaurantSearchModel restaurantSearchModel, Status status) {
		// An admin should be able to approve/reject a given dining Review
		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new NotFoundException("Given restaurant does not exist");
		}
		Restaurant restaurant = fetchRestaurantById.get();

		List<DiningReview> reviewToApproveOrReject = diningReviewRepository.getByRestaurantAndStatusEquals(restaurant,
				Status.PENDING);

		Optional<DiningReview> diningReviewById = diningReviewRepository.findById(restaurantSearchModel.getReviewId());

		if (!diningReviewById.isPresent()) {
			throw new NotFoundException("Not diningReview has the assigned Id");
		}
		DiningReview foundreviewById = diningReviewById.get();

		for (DiningReview diningReview : reviewToApproveOrReject) {
			if (diningReview.getDiningReviewId() == foundreviewById.getDiningReviewId()) {
				// call AdminReview Operation on diningReview Status here DiningReview
				foundreviewById = AdminReview.acceptOrRejectDiningReviewStatusByUser(diningReview, status);
				diningReviewRepository.save(foundreviewById);
				break;
			} else {
				throw new NotFoundException("No reviewId matched the provided diningReviewId");
			}
		}
		return foundreviewById;
	}

	public List<DiningReview> retrieveAllApprovedReviewsforAGivenRestaurant(
			RestaurantSearchModel restaurantSearchModel) {
		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new NotFoundException("Given restaurant does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();

			List<DiningReview> allApprovedReviewsforGivenRestaurant = diningReviewRepository
					.getAllByRestaurantAndStatusEquals(restaurant, Status.ACCEPTED);

			return allApprovedReviewsforGivenRestaurant;
		}
	}

}
