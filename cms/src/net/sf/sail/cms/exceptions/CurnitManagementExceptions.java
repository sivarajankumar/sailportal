package net.sf.sail.cms.exceptions;

public abstract class CurnitManagementExceptions extends Throwable {
	private String errorCode;
	private String errorMessage;
	
	public CurnitManagementExceptions() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
