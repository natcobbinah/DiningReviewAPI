package com.example.DiningReviewApi.DataModels;

import java.util.Optional;

import com.example.DiningReviewApi.DiningReviews.DiningReview;
import com.example.DiningReviewApi.DiningReviews.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminReview {

	public static DiningReview acceptOrRejectDiningReviewStatusByUser(DiningReview diningReview, Status status) {
		 diningReview.setStatus(status);
		 return diningReview;
	}
}
