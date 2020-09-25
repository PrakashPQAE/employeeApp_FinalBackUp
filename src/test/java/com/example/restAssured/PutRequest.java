package com.example.restAssured;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PutRequest {
	

	@Test
	public void testGetendPoint() throws URISyntaxException {
		
	               RestAssured.given()
	               .body("{\r\n" + 
				   		"\"name\": \"Alex Twis\",\r\n" + 
				   		"\"role\": \"Senior Manager 2\"\r\n" + 
				   		"}")
				   .when().put(new URI("http://localhost:8080/employees/{id}"))
				   .then()
				   .assertThat()
				   .statusCode(200).log().all();		
		
		 
	}

}
