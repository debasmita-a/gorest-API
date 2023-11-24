package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;

public class CreateUserTest  extends BaseTest{

	@Test
	public void createUserTest(){
		
		User user = new User("Debasmita", "gorest_" + System.currentTimeMillis() + "@api.com", "Female", "active");
		
		int userId = restClient.post("/public/v2/users", "JSON", user, true, true)
		            .then().log().all()
		                .assertThat()
		                    .statusCode(201)
		                         .extract()
		                             .path("id");
		  System.out.println("User id is : " + userId) ;
		  
		  restClient.get("/public/v2/users/" + userId ,true, true)
               .then().log().all()
                   .assertThat()
                       .statusCode(200)  
                           .and()
                              .body("id", equalTo(userId));
		            
	}
}
