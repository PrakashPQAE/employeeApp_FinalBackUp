package stepDef;

import java.net.URI;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;

public class ValidateWireMockGetRequest {	
	
	public static final int PORT = 8080;
	//public static final String HOST = "localhost";
	public static WireMockServer wiremockserver = new WireMockServer(PORT);
	
	 @Given("^WireServer is started$")
	    public void wireserver_is_started() throws Throwable {
		 
		 wiremockserver.start();	
	     
	    }
	
    @Given("^Stubbing of wireMock for get request with (.+) and (.+) and (.+)$")
    public void stubbing_of_wiremock_for_get_request_with_and_and(String host, int port, int stubstatus) throws Throwable {
    	 
    		
		ResponseDefinitionBuilder responseMock = new ResponseDefinitionBuilder();
		responseMock.withStatus(stubstatus);
		WireMock.configureFor(host,port);
		WireMock.stubFor(
				WireMock.get("/employees").willReturn(responseMock));	
    }

    @When("^assertion of status code in the get response with http (.+)$")
    public void assertion_of_status_code_in_the_get_response_with_http(int status) throws Throwable {
       
    	 RestAssured.given()
		   .when().get(new URI("http://localhost:8080/employees"))
		   .then()
		   .assertThat()
		   .statusCode(status).log().all().extract().response();
    }

    @Then("^wiremock is shut down$")
    public void wiremock_is_shut_down() throws Throwable {    	

		/*
		 * if(null != wiremockserver && wiremockserver.isRunning()) {
		 * wiremockserver.shutdownServer();
		 * 
		 * }
		 */
     	wiremockserver.shutdownServer();
     
    	wiremockserver.stop();
            
    }
  
	

}
