package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	//private static final String BASE_URL = "https://gorest.co.in";
	//private static final String BEARER_TOKEN = "5e874cd5ea5d4dcf22fd42eb9dc03bd89c67e7c2483b4c2a24d8e8e25814857f";
	private RequestSpecBuilder specBuilder;

	private Properties prop;
	private String baseURI;
	
	private boolean isAuthHeaderToBeAdded = false;
	
	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}
	
	private void addAuthorizationHeader() {
		if(!isAuthHeaderToBeAdded) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
		isAuthHeaderToBeAdded = true;
		}
	}

	private void setRequestContentType(String contentType) {
		switch(contentType.toLowerCase()) {
		case "json" :
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "text" :
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "xml" :
			specBuilder.setContentType(ContentType.XML);
			break;
		case "multipart" :
			specBuilder.setContentType(ContentType.MULTIPART);
			break;	
		default:
			System.out.println("Please pass the correct content type.");
			throw new APIFrameworkException("INVALIDCONTENTTYPE");
		}
	}   	 

	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth == true) {
		addAuthorizationHeader();
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);

		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		addAuthorizationHeader();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParamsMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth == true) {
			addAuthorizationHeader();
			}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParamsMap != null) {
			specBuilder.addQueryParams(queryParamsMap);
		}
		addAuthorizationHeader();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth == true) {
			addAuthorizationHeader();
			}
		setRequestContentType(contentType);
		if(requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		addAuthorizationHeader();
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth == true) {
			addAuthorizationHeader();
			}
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if(requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		addAuthorizationHeader();
		return specBuilder.build();
	}
	
	//Http Methods Utils :
	
	public Response get(String serviceUrl, boolean includeAuth, boolean log) {		
		if(log == true) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		           .when().get(serviceUrl);		           
		}		
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);	           
	} 
	
	public Response get(String serviceUrl, Map<String, String> headersMap, boolean includeAuth, boolean log) {		
		if(log == true) {
		return RestAssured.given(createRequestSpec(headersMap, includeAuth)).log().all()
		           .when()
		           .get(serviceUrl);
		}		
		return RestAssured.given(createRequestSpec(headersMap, includeAuth)).when().get(serviceUrl);	           
	} 
	
	public Response get(String serviceUrl, Map<String, String> headersMap, Map<String, String> queryParamsMap, boolean includeAuth, boolean log) {		
		if(log == true) {
		return RestAssured.given(createRequestSpec(headersMap, queryParamsMap, includeAuth)).log().all()
		           .when()
		           .get(serviceUrl);
		}		
		return RestAssured.given(createRequestSpec(headersMap, queryParamsMap, includeAuth)).when().get(serviceUrl);	           
	} 
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
			            .when()
			                 .post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().post(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
			            .when()
			                 .post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().post(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
			            .when()
			                 .put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().put(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
			            .when()
			                 .put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().put(serviceUrl);
	}
		
	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
			            .when()
			                 .patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).when().patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
			            .when()
			                 .patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).when().patch(serviceUrl);
	}
	
	public Response delete(String serviceUrl, boolean includeAuth, boolean log) {
		if(log == true) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		           .when()
		           .delete(serviceUrl);
		}		
		return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
	}

}
