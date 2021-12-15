Feature: Validating Place APIs

  Scenario: Verify Add Place API is successfully adding place
    Given Add Place payload
    When User calls "AddPlaceAPI" with POST request
    Then The API call is successful with status code 200
    And "Status" in response body is "OK"
    And "Scope" in response body is "APP"
