# DiningReviewAPI
Implementation of diningReviewAPI codecademy portfolio project challenge on Spring/SpringBoot(still in progress...subject to modifications)

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

## restAPIEndpoints
* USERS

    1.  findUserByName 
        > GET http://localhost:8080/api/v1/user?name="username_here"
  
    2.  createUser
        > POST http://localhost:8080/api/v1/user
        Headers: Content-Type: application/json
        body sample
          {
            "displayName": "string",
            "userAddress": {
              "zipCode": "string",
              "city": "string"
          },
          "interestPeanutAllergies": boolean,
          "interestEggAllergies": boolean,
          "interestDiaryAllergies": boolean
          }

    3. updateUser
        > PUT http://localhost:8080/api/v1/user
        Headers: Content-Type: application/json
        body sample
        {
            "displayName": "string",
            "userAddress": {
              "zipCode": "string",
                "city": "string"
            },
            "interestPeanutAllergies": boolean,
            "interestEggAllergies": boolean,
            "interestDiaryAllergies": boolean
          }

  * RESTAURANT
      1. fetchRestaurantByID
      > GET http://localhost:8080/api/v1/restaurant/{restaurantId_here}
        Headers: Content-Type: application/json

      2. fetchRestaurantWithReviewsByZipCode
      > GET http://localhost:8080/api/v1/restaurant?zipCode=zipCode_here
        Headers: Content-Type: application/json

      3. createRestaurant
      > POST http://localhost:8080/api/v1/restaurant
      Headers: Content-Type: application/json
      body sample
        {
          "restaurantAddress":{
          "name" : "string",
          "zipCode" : "string"
          },
          "peanutAllergyScore": "integer",
          "eggAllergyScore" : "integer",
          "dairyAllergyScore": "integer",
        }

  * REVIEWS
      1. getAllPendingReviews
        > GET http://localhost:8080/api/v1/diningReview/pending

      2. retrieveAllApprovedReviewsforAGivenRestaurant
      > GET http://localhost:8080/api/v1/diningReview/all/approved?restaurantId={restaurantId_here}

      3. submitDiningReview
      > POST http://localhost:8080/api/v1/diningReview
      Headers: Content-Type: application/json
      body sample
        {
          "reviewerName" : "string",
          "restaurantId" : "integer",
          "peanutAllergyScore" : "Enum(1..5/ONE..FIVE)",
          "eggAllergyScore": "Enum(1..5/ONE..FIVE)",
          "dairyAllergyScore" : "Enum(1..5/ONE..FIVE)",
          "commentary" : "string"
      }

  

 
