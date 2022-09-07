package com.example.DiningReviewApi.DiningReviews;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {

}
