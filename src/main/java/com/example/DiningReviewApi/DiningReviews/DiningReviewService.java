package com.example.DiningReviewApi.DiningReviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DiningReviewApi.DataModels.AdminReview;
import com.example.DiningReviewApi.DataModels.DiningReviewDataModel;
import com.example.DiningReviewApi.DataModels.RestaurantSearchModel;
import com.example.DiningReviewApi.Restaurant.Restaurant;
import com.example.DiningReviewApi.Restaurant.RestaurantRepository;

@Service
public class DiningReviewService {

	@Autowired
	DiningReviewRepository diningReviewRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	public DiningReview submitDiningReview(DiningReviewDataModel diningReviewDataModel,
			RestaurantSearchModel restaurantSearchModel) throws Exception {
		// a registeredUser should be able to give a diningReview
		// select restaurant to give review either by (ID/ADDRESS/ZipCode)

		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new Exception("Restaurant with the givenId does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();

			DiningReview diningReview = new DiningReview();
			diningReview.setReviewerName(diningReviewDataModel.getReviewerName());
			diningReview.setRestaurant(restaurant);
			diningReview.setPeanutAllergyScore(diningReviewDataModel.getPeanutAllergyScore());
			diningReview.setEggAllergyScore(diningReviewDataModel.getEggAllergyScore());
			diningReview.setDairyAllergyScore(diningReviewDataModel.getDairyAllergyScore());
			diningReview.setStatus(diningReviewDataModel.getStatus());
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

	public DiningReview approveAndRejectAGivenDiningReview(RestaurantSearchModel restaurantSearchModel,
			Status status) throws Exception {
		// An admin should be able to approve/reject a given dining Review
		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		DiningReview diningReviewResponse = new DiningReview();

		if (!fetchRestaurantById.isPresent()) {
			throw new Exception("Given restaurant does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();

			Optional<DiningReview> reviewToApproveOrReject = diningReviewRepository
					.getByRestaurantAndStatusEquals(restaurant, Status.PENDING);

			if (!reviewToApproveOrReject.isPresent()) {
				System.out.println("Given restaurant has no pending reviews");
			} else {
				DiningReview diningReview = reviewToApproveOrReject.get();

				// call AdminReview Operation on diningReview Status here
				DiningReview finalizedDiningReview = AdminReview.acceptOrRejectDiningReviewStatusByUser(diningReview, status);
				diningReviewResponse = finalizedDiningReview;
				diningReviewRepository.save(finalizedDiningReview);
			}
		}
		return diningReviewResponse;
	}

	public List<DiningReview> retrieveAllApprovedReviewsforAGivenRestaurant(RestaurantSearchModel restaurantSearchModel)
			throws Exception {
		Optional<Restaurant> fetchRestaurantById = restaurantRepository.findById(restaurantSearchModel.getId());

		if (!fetchRestaurantById.isPresent()) {
			throw new Exception("Given restaurant does not exist");
		} else {
			Restaurant restaurant = fetchRestaurantById.get();

			List<DiningReview> allApprovedReviewsforGivenRestaurant = diningReviewRepository
					.getAllByRestaurantAndStatusEquals(restaurant, Status.ACCEPTED);

			return allApprovedReviewsforGivenRestaurant;
		}
	}

}
