Feature: Validating Place APIs

  Scenario Outline: Verify Add Place API is successfully adding place
    Given Add Place payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceApi" with "POST" request
    Then The API call is successful with status code "200"
    And "Status" in response body is "OK"
    And "Scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name      | language  | address             |
      | AA House  | English   | World Cross Center  |
      | BB House  | Spanish   | Sea Cross Center    |