package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class GetUserTest extends BaseTest{
	
	@Test
	public void getAllUsersTest() {
		restClient = new RestClient(prop, baseURI);
		restClient.get("/public/v2/users", true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200)  
		                        .and()
		                             .body("$", hasSize(10));
		            
	}
	
	@Test
	public void getUserTest() {
		restClient = new RestClient(prop, baseURI);
		restClient.get("/public/v2/users/5765574", true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200)  
		                        .and()
		                          .body("id", equalTo(5765574));
		            
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
		restClient = new RestClient(prop, baseURI);
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "Debasmita");
		queryParamsMap.put("status", "inactive");
		
		Map<String, String> headersMap = new HashMap<String, String>();
		
		restClient.get("/public/v2/users/5765574", headersMap, queryParamsMap,true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200)  
		                        .and()
		                          .body("name", equalTo("Debasmita"));
		                             
		            
	}
}
