package com.p.service.response;

public class SuccessResponse extends GenericResponse{
	
	Object responseObject;

	
	
	public SuccessResponse(String responseCode, String responseMessage, Object responseObject) {
		super(responseCode, responseMessage);
		this.responseObject = responseObject;
	}



	public Object getResponseObject() {
		return responseObject;
	}



	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	
	
}
