# DiningReviewAPI
Implementation of diningReviewAPI codecademy portfolio project challenge on Spring/SpringBoot

- [NOTE] Inbuilt h2 database is used to persist to data to file, so no need for an external db

- Clone project, and run in your preferred IDE to review and testAPI endpoints

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
- A restaurant reviewScores for individual foodAllergies and overAll Scores get updated on userReview entries
 computing the average across all submitted scores for that category **and** overAll restaurant Score, by computing
 the average across all submitted scores across all categories
 
 ## Testing the apiEndpoints for the various functionalities
 ```
 Can be tested using any suitable restAPI test tools (PostMan, ARC(Advanced Rest Client a chrome extension) or curl
* USERS
      1. Creating a User
         > POST http://localhost:8080/api/v1/user
         Headers: Content-Type: application/json
         body sample
          {
              "displayName": "String",
              "userAddress": {
                "zipCode": "String",
                  "city": "String"
              },
              "interestPeanutAllergies": boolean,
              "interestEggAllergies": boolean,
              "interestDiaryAllergies": boolean
            } 
       
     2. Find a user by userName
       > GET http://localhost:8080/api/v1/user?name="username_goes_here"
       Headers: Content-Type: application/json
       
     3. Update A User
       >PUT http://localhost:8080/api/v1/user
        Headers: Content-Type: application/json
        body sample
         {
              "displayName": "String",
              "userAddress": {
                "zipCode": "String",
                  "city": "String"
              },
              "interestPeanutAllergies": boolean,
              "interestEggAllergies": boolean,
              "interestDiaryAllergies": boolean
          } 
    
* RESTAURANT
      1. Creating a Restaurant
         > POST http://localhost:8080/api/v1/restaurant
         Headers: Content-Type: application/json
         body sample
         {
            "restaurantAddress":{
              "name" : "String",
              "zipCode" : "String"
            }
         }
      
      2. Fetch All Restaurants
      > GET http://localhost:8080/api/v1/restaurant
        Headers: Content-Type: application/json
       
      3. Fetch Restaurant ByID
      > GET http://localhost:8080/api/v1/restaurant/{restaurantId_here}
                
                
      4. fetch Restaurant With Reviews ByZipCode
      > GET http://localhost:8080/api/v1/restaurant?zipCode={zipCode_here}
        Headers: Content-Type: application/json
        
* REVIEWS
     1. Submit a Review
         > POST http://localhost:8080/api/v1/diningReview
         Headers: Content-Type: application/json
         body sample
         {
            "reviewerName" : "String",
              "restaurantId" : "Integer",
              "peanutAllergyScore" : "Enum(ONE/TWO/THREE/FOUR/FIVE)",
              "eggAllergyScore": "Enum(ONE/TWO/THREE/FOUR/FIVE)",
              "dairyAllergyScore" : "Enum(ONE/TWO/THREE/FOUR/FIVE)",
              "commentary" : "String"
         }
       
      2. Retrieve all pending dining reviews
      > GET http://localhost:8080/api/v1/diningReview/pending
        Headers: Content-Type: application/json
        
      3. Retrieve all approved reviews for a given restaurant
      > GET http://localhost:8080/api/v1/diningReview/all/approved?restaurantId={restaurantID_here}
     
* ADMIN
     1. Approve/Reject a given diningReview
      > PATCH http://localhost:8080/api/v1/diningReviews/{restaurantID_here}?status={STATUS}&reviewId={reviewID}
         
       
       

                
      
      

 ```
 
