package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class ReqresTest  extends BaseTest{


	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getAllUsersTest() {
		
		restClient.get("/api/uers/2", false, false)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200);  
		                        
		            
	}
}
