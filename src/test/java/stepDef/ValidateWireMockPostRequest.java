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

public class ValidateWireMockPostRequest {	

	public static final int PORT = 8080;
	//public static final String HOST = "localhost";
	public static WireMockServer wiremockserver = new WireMockServer(PORT);


	@Given("^Stubbing of wireMock for post request with (.+) and (.+) and (.+)$")
	public void stubbing_of_wiremock_for_post_request_with_and_and(String host, int port, int stubstatus) throws Throwable {
		 
		//wiremockserver.start();		
		ResponseDefinitionBuilder responseMock = new ResponseDefinitionBuilder();
		responseMock.withStatus(stubstatus);
		WireMock.configureFor(host,port);
		WireMock.stubFor(
				WireMock.post("/employees").willReturn(responseMock));	
	}



	@When("^Post request is processed with the body (.+) and (.+) and (.+) and (.+) and (.+) and (.+) and (.+) and assert the status code with http status (.+)$")
	public void post_request_is_processed_with_the_body_and_and_and_and_and_and_and_assert_the_status_code_with_http_status(String name, String role, String firstname, String lastname, String email, String password, String phone, int status) throws Throwable {

		RestAssured.given()
		.body("{\r\n" + 
				"    \"name\": \"+"+name+"\",\r\n" + 
				"    \"role\": \"+"+role+"\",\r\n" + 
				"    \"firstname\": \"+"+firstname+"\",\r\n" + 
				"    \"lastname\": \"+"+lastname+"\",\r\n" + 
				"    \"email\": \"+"+email+"\",\r\n" + 
				"    \"password\": \"+"+password+"\",\r\n" + 
				"    \"phone\": \"+"+phone+"\"\r\n" + 
				" }")
		.when().post(new URI("http://localhost:8080/employees"))
		.then()
		.assertThat()
		.statusCode(status).log().all();

	}

 	
	 @Then("^wiremock is shut down successfully$")
	    public void wiremock_is_shut_down_successfully() throws Throwable {
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
