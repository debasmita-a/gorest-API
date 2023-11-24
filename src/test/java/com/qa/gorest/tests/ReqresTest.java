package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class ReqresTest  extends BaseTest{


	@Test
	public void getAllUsersTest() {
		
		restClient.get("/api/uers/2", false, false)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200);  
		                        
		            
	}
}
