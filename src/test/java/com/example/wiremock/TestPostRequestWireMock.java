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

public class TestPostRequestWireMock {	
	
	
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
				WireMock.post("/employees").willReturn(responseMock));		
	}
		  
 
	@Test
	public void testGetendPoint() throws URISyntaxException {
		
		RestAssured.given()
				   .body("{\r\n" + 
				   		"    \"name\": \"Larler\",\r\n" + 
				   		"    \"role\": \"Tester\",\r\n" + 
				   		"    \"firstname\": \"Lasrler\",\r\n" + 
				   		"    \"lastname\": \"Laest\",\r\n" + 
				   		"    \"email\": \"email@gmail.com\",\r\n" + 
				   		"    \"password\": \"pwd12345\",\r\n" + 
				   		"    \"phone\": \"9999009265\"\r\n" + 
				   		" }")
				   .when().post(new URI("http://localhost:8080/employees"))
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
