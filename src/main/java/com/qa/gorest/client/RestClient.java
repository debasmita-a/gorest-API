package com.qa.gorest.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static final String BASE_URL = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f";
	private static RequestSpecBuilder specBuilder;
	
	static {
		specBuilder = new RequestSpecBuilder();
	}
	
	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
	}
	
	public RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URL);
		addAuthorizationHeader();
		return specBuilder.build();
	}
}
