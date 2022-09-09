package com.example.DiningReviewApi.DataModels;

import com.example.DiningReviewApi.DiningReviews.DiningReview;
import com.example.DiningReviewApi.DiningReviews.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminReview {

	public static DiningReview acceptOrRejectDiningReviewStatusByUser(DiningReview diningReview) {
		 diningReview.setStatus(Status.ACCEPTED);
		 return diningReview;
	}
}
