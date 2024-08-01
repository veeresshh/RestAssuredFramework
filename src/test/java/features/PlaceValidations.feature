Feature: Validating Place API's

@AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http Request
    Then The API Call is Success with Status code 200
    And "status" in Response body is "OK"
    And "scope" in Response body is "APP"
    And verify Place_ID created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name    | language | address   |
      | VEERESH | ENGLISH  | BANGALORE |

@DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "POST" http Request
    Then The API Call is Success with Status code 200
    And "status" in Response body is "OK"
    And "scope" in Response body is "APP"
