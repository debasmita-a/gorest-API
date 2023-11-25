package com.qa.gorest.tests;

import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class CircuitTest extends BaseTest{

	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllCircuitsTest() {
		
		restClient.get("/api/f1/circuits",false, false)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200);  
		                        
		            
	}
}
