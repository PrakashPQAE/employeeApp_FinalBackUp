package com.example.restAssured;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRequest {
	
	 
		@Test
		public void testGetendPoint() throws URISyntaxException {
			
		               RestAssured.given()
					   .when().delete(new URI("http://localhost:8080/employees/{id}"))
					   .then()
					   .assertThat()
					   .statusCode(200).log().all();		 
		}

}
