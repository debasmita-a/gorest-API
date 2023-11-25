package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class GetUserTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test(priority = 3)
	public void getAllUsersTest() {
		restClient.get("/public/v2/users", true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200)  
		                        .and()
		                             .body("$", hasSize(10));
		            
	}
	
	@Test(priority = 2)
	public void getUserTest() {
		restClient.get("/public/v2/users/5765574", true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200)  
		                        .and()
		                          .body("id", equalTo(5765574));
		            
	}
	
	@Test(priority = 1)
	public void getUserWithQueryParamsTest() {
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
