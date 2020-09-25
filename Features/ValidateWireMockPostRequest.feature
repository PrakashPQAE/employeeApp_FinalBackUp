Feature: Validation of wiremock server Post call

Background:
    Given WireServer is started
        
@postWireMock
Scenario Outline: To verify the Post call on wiremock server - multiple data insertion
Given Stubbing of wireMock for post request with <host> and <port> and <stubstatus>
When Post request is processed with the body <name> and <role> and <firstname> and <lastname> and <email> and <password> and <phone> and assert the status code with http status <status>


Examples:
|stubstatus|host     |port|name   |role       |firstname|lastname|email            |password|phone     |status|
|200       |localhost|8080|Charles|Developer  |Charles  |Robert  |charles@gmail.com|123poiuc|9900992872|200   |
|200       |localhost|8080|Charles|Test Senior|Daisy    |John    |Daisy34@gmail.com|345loiuj|9876543238|200   |
