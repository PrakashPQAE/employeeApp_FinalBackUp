Feature: Validation of wiremock server put call

Background:
    Given WireServer is started
    
@putWireMock
Scenario Outline: To verify the put call on wiremock server - multiple data as paramaters
Given Stubbing of wireMock for put request with <host> and <port> and <stubstatus> with path param <id>
When put request is processed with the body <name> and <role> and <firstname> and <lastname> and <email> and <password> and <phone> and assert the status code with http status <status> for id <id>

Examples:
|stubstatus|host     |port|name   |role       |firstname|lastname|email            |password|phone     |id|status|
|200       |localhost|8080|Charles|Developer  |Charles  |Robert  |charles@gmail.com|123poiuc|9900992872|1 |200   |
|200       |localhost|8080|Charles|Test Senior|Daisy    |John    |Daisy34@gmail.com|345loiuj|9876543238|2 |200   |

