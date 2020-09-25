package com.example.wiremock;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestDeleteRequestWireMock {		
	
	public static final int PORT = 8080;
	public static final String HOST = "localhost";
	public static WireMockServer wiremockserver = new WireMockServer(PORT);
		
	
	@BeforeClass
	public static void setup() {
		
		wiremockserver.start();		
		ResponseDefinitionBuilder responseMock = new ResponseDefinitionBuilder();
		responseMock.withStatus(200);
		WireMock.configureFor(HOST,PORT);
		WireMock.stubFor(
				WireMock.delete("/employees").willReturn(responseMock));		
	}
		  
 
	@Test
	public void testGetendPoint() throws URISyntaxException {
		
	               RestAssured.given()
				   .when().delete(new URI("http://localhost:8080/employees/{id}"))
				   .then()
				   .assertThat()
				   .statusCode(200).log().all();		 
	}
	
	@AfterClass
	public void teardown() {
		
		if(null != wiremockserver && wiremockserver.isRunning()) {
			wiremockserver.shutdownServer();
		}
	}

}
