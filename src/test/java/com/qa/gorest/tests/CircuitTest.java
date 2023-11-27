package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class CircuitTest extends BaseTest{

	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllCircuitsTest() {
		
		restClient.get(CIRCUIT_ENDPOINT + "circuits",false, false)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(APIHttpStatus.OK_200.getCode());  
		                        
		            
	}
}
