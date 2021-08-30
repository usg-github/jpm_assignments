package intw.jpm.exception;

public class ExternalServiceException extends RuntimeException{

	private static final long serialVersionUID = 8683755066007599812L;



	public ExternalServiceException(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	private Integer errorCode;

	private String message;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
