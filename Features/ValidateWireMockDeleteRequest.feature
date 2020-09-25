Feature: Validation of wiremock server delete call

Background:
    Given WireServer is started
    
@deleteWireMock
Scenario Outline: To verify the delete call on wiremock server - valid parameters
Given Stubbing of wireMock for delete request with <host> and <port> and <stubstatus> with path param <id>
When assertion of status code in the delete response with http <status> and id <id>

Examples:
|status|host     |port|id|stubstatus|
|200   |localhost|8080|1 |200       |
|200   |localhost|8080|2 |200       |

