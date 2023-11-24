package com.qa.gorest.tests;

import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class CircuitTest extends BaseTest{


	@Test
	public void getAllCircuitsTest() {
		
		restClient.get("/api/f1/circuits",false, false)
		            .then().log().all()
		               .assertThat()
		                   .statusCode(200);  
		                        
		            
	}
}
