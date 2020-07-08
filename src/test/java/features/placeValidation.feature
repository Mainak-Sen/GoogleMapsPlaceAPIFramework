Feature: Validating Place API
@AddPlace @Regression
Scenario Outline: Verify if place is getting successfully added using ADD_PLACE_API
Given Add Place payload with "<name>" "<phone_number>" "<address>" "<language>" 
When User calls "ADD_PLACE_API" with "POST" http request
Then The API call returns status code 200
And The "status" displayed in the response body should be "OK"
And The "scope" displayed in the response body should be "APP"
And Verify if the placeID has mapped "name" to the desired "<name>" using "GET_PLACE_API"

Examples:
|name			        |phone_number      |address                     |language|
|WeedLand@93            |(+91) 983 893 3907|FA/9 Deshbandhunagar,Kolkata|Bengali |
#|AbodeOfStoners@93      |(+91) 923 863 3907|Pimple Saudagar,Pune        |English |
@UpdatePlace @Regression
Scenario Outline: Verify if address is getting updated successfully using UPDATE_PLACE_API
Given UpdatePlace payload with "<updated_address>" details
When User calls "UPDATE_PLACE_API" with "PUT" http request
Then The API call returns status code 200
And The "msg" displayed in the response body should be "Address successfully updated"
And Verify if the placeID has mapped "address" to the desired "<updated_address>" using "GET_PLACE_API"

Examples:
|updated_address                        |
|StonerLand, WeedParadise FA/9 POTSTREET| 
@DeletePlace @Regression
Scenario: Verify if place is getting successfully deleted using DELETE_PLACE_API
Given DeletePlace payload
When User calls "DELETE_PLACE_API" with "POST" http request
Then The API call returns status code 200
And The "status" displayed in the response body should be "OK"
When User calls "GET_PLACE_API" with "GET" http request
Then The API call returns status code 404
And The "msg" displayed in the response body should be "Get operation failed, looks like place_id  doesn't exists"