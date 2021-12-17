Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify Add Place API is successfully adding place
    Given Add Place payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceApi" with "POST" request
    Then The API call is successful with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "GetPlaceApi"

    Examples:
      | name      | language  | address             |
      | AA House  | English   | World Cross Center  |
#      | BB House  | Spanish   | Sea Cross Center    |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace payload
    When User calls "DeletePlaceApi" with "POST" request
    Then The API call is successful with status code "200"
    And "status" in response body is "OK"