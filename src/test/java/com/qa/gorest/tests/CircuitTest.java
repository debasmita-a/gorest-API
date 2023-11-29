package com.qa.gorest.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends BaseTest{

	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllCircuitsTest() {
		Response circuitResponse = restClient.get(CIRCUIT_ENDPOINT + "circuits",false, false);
			
		circuitResponse.then().log().all()
		               .assertThat()
		                   .statusCode(APIHttpStatus.OK_200.getCode());
		                      
		JsonPathValidator js = new JsonPathValidator(); 
		List<String> countryList = js.readList(circuitResponse, "$.MRData.CircuitTable.Circuit.Location.Country");
		System.out.println(countryList);
		            
	}
}
