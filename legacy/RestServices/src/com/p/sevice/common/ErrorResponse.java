package com.p.sevice.common;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorResponse.
 */
public class ErrorResponse {

	
	/** The error code. */
	private String errorCode;
    
    /** The error msge. */
    private String errorMsge;
    
    private String errorDetail="NA";
    
    public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}
    
    
        
	public ErrorResponse(String errorCode, String errorMsge) {
		super();
		this.errorCode = errorCode;
		this.errorMsge = errorMsge;
	}



	public ErrorResponse(String errorCode, String errorMsge, String errorDetail) {
		super();
		this.errorCode = errorCode;
		this.errorMsge = errorMsge;
		this.errorDetail = errorDetail;
	}



	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the error msge.
	 *
	 * @return the error msge
	 */
	public String getErrorMsge() {
		return errorMsge;
	}
	
	/**
	 * Sets the error msge.
	 *
	 * @param errorMsge the new error msge
	 */
	public void setErrorMsge(String errorMsge) {
		this.errorMsge = errorMsge;
	}



	public String getErrorDetail() {
		return errorDetail;
	}



	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
    
}
