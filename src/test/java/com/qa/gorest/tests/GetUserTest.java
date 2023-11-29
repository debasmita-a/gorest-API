package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.qameta.allure.Description;

public class GetUserTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	@Description("This test is not complete.")
	@Test(enabled = true, priority = 3)
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT, true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(APIHttpStatus.OK_200.getCode())  
		                        .and()
		                             .body("$", hasSize(10));
		            
	}
	
	@Test(priority = 2)
	public void getUserTest() {
		restClient.get(GOREST_ENDPOINT+"/5765574", true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(APIHttpStatus.OK_200.getCode())  
		                        .and()
		                          .body("id", equalTo(5765574));
		            
	}
	
	@Test(priority = 1)
	public void getUserWithQueryParamsTest() {
		Map<String, Object> queryParamsMap = new HashMap<String, Object>();
		queryParamsMap.put("name", "Debasmita");
		queryParamsMap.put("status", "inactive");
		
		Map<String, String> headersMap = new HashMap<String, String>();
		
		restClient.get(GOREST_ENDPOINT, headersMap, queryParamsMap,true, true)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(APIHttpStatus.OK_200.getCode());                            
		            
	}
}
