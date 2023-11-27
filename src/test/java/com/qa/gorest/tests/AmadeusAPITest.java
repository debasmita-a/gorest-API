package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{

	private String access_token;
	
	@Parameters({"baseURI","grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightAPIsetUp(String baseURI, String grantType, String clientId, String clientSecret) {
		restClient = new RestClient(prop, baseURI);
		access_token = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);		
	}
	
	@Test
	public void getFlightPriceListTest() {
		RestClient restClientFlight = new RestClient(prop, baseURI);
		
		Map<String, Object> queryParamsMap = new HashMap<String, Object>();
		queryParamsMap.put("origin", "PAR");
		queryParamsMap.put("maxPrice", 200);
		
		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Authorization", "Bearer " + access_token);
		
		Response responseFlight = restClientFlight.get(AMADEUS_FLIGHT_BOOKING_ENDPOINT, headersMap, queryParamsMap, false, true)
		                .then().log().all()
		                .assertThat()
		                .statusCode(APIHttpStatus.OK_200.getCode())
		                .and()
		                .extract()
		                .response();
		JsonPath js = responseFlight.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type); //flight-destination	
	}
	
	
}
