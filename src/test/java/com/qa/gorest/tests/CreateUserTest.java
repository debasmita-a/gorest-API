package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.FrameworkConstants;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateUserTest  extends BaseTest{
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}	
	
	@DataProvider
	public Object[][] getUserTestData(){
		return new Object[][] {
			{"Kikimora", "Female", "Inactive"},
			{"Domovoi", "Male", "Active"}
		};
	}
	
	@DataProvider
	public Object[][] getUserTestSheetData(){
		return ExcelUtil.getTestData(FrameworkConstants.GOREST_USER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getUserTestData")
	public void createUserTest(String name, String gender, String status){
		
		User user = new User(name, "gorest_" + System.currentTimeMillis() + "@api.com", gender, status);
		
		int userId = restClient.post(GOREST_ENDPOINT, "JSON", user, true, true)
		            .then().log().all()
		                .assertThat()
		                    .statusCode(APIHttpStatus.CREATED_201.getCode())
		                         .extract()
		                             .path("id");
		  System.out.println("User id is : " + userId) ;
		  
		  RestClient restClientGet = new RestClient(prop, baseURI);
		  restClientGet.get(GOREST_ENDPOINT + userId ,true, true)
               .then().log().all()
                   .assertThat()
                       .statusCode(APIHttpStatus.OK_200.getCode())  
                           .and()
                              .body("id", equalTo(userId));
		            
	}

}
