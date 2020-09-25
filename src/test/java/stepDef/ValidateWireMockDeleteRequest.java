package stepDef;

import java.net.URI;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;

public class ValidateWireMockDeleteRequest {	

	public static final int PORT = 8080;
	//public static final String HOST = "localhost";
	
	public static WireMockServer wiremockserver = new WireMockServer(PORT);
		
    @Given("^Stubbing of wireMock for delete request with (.+) and (.+) and (.+) with path param (.+)$")
    public void stubbing_of_wiremock_for_delete_request_with_and_and_with_path_param(String host, int port, int stubstatus, int id) throws Throwable {
    	 
    	//wiremockserver.start();		
		ResponseDefinitionBuilder responseMock = new ResponseDefinitionBuilder();
		responseMock.withStatus(stubstatus);
		WireMock.configureFor(host,port);
		WireMock.stubFor(
				WireMock.delete("/employees/"+id+"").willReturn(responseMock));	
    	
    	
    }

    @When("^assertion of status code in the delete response with http (.+) and id (.+)$")
    public void assertion_of_status_code_in_the_delete_response_with_http_and_id(int status, int id) throws Throwable {
       
    	RestAssured.given()
	    .when().delete(new URI("http://localhost:8080/employees/"+id+""))
		.then()
		.assertThat()
		.statusCode(status).log().all();    	
    }

    @Then("^wiremock is shut down successfully for delete request$")
    public void wiremock_is_shut_down_successfully_for_delete_request() throws Throwable {
    	
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


