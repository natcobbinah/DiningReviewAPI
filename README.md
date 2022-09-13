# DiningReviewAPI
Implementation of diningReviewAPI codecademy portfolio project challenge on Spring/SpringBoot(still in progress...subject to modifications)

- [NOTE] Inbuilt h2 database is used to persist to data to file, so no need for an external db

## Scenario
Given a [User, Admin, Restaurant, diningReviews] create restEndpoints such that:
- A user can create/update/ and fetchUser profile belonging to a given displayName
- A user can submit diningReviews
- An admin can list all dining reviews pending approval
- An admin can approve/reject a given dining review
- An admin can fetch all approved diningReviews belonging to a restaurant
- Sumbit a new restaurantEntry (If a restaurant with sameName and sameZipCode exist) report failure
- Fetch details of a restaurant given its uniqueId
- Fetch restaurants that match a given zipCode and has at least one-submitted score for a given allergy sorted in
  descending order
