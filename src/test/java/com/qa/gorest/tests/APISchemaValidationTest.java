package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;

import io.restassured.module.jsv.JsonSchemaValidator;

public class APISchemaValidationTest extends BaseTest{

	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void createUserAPISchemaTest() {
		
		User user = new User("Timothy", "gorest_" + System.currentTimeMillis() + "@api.com", "Male", "Active");
		
		restClient.post(GOREST_ENDPOINT, "JSON", user, true, true)
		              .then().log().all()
		                  .assertThat()
		                      .statusCode(APIHttpStatus.CREATED_201.getCode())
		                           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUserSchema.json"));
	}
}
