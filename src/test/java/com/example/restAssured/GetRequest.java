package com.example.restAssured;

import java.net.URI;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class GetRequest {
	
	@Test
	public void testGetendPoint() throws URISyntaxException {
		
		            RestAssured.given()
				   .when().get(new URI("http://localhost:8080/employees"))
				   .then()
				   .assertThat()
				   .statusCode(200).log().all();		
		 
		 
	}

}
