package intw.jpm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import intw.jpm.utils.DateAndTimeUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest request) {

		  ErrorResponse errorResponse = ErrorResponse.builder()
				  							.date(DateAndTimeUtils.getResponseDate())
				  							.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				  							.error( "Validation error. Check 'errors' field for details.")
				  							.success(Boolean.FALSE)
				  							.build();

	    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	    	errorResponse.addValidationError(fieldError.getField(),
	          fieldError.getDefaultMessage());
	    }

	    return ResponseEntity.unprocessableEntity()
	  		  .body(errorResponse);
	  }

	@ExceptionHandler(value= {ExternalServiceException.class})
	public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException exception) {
		return ResponseEntity.ok()
				.body(
						ErrorResponse.builder()
						.date(DateAndTimeUtils.getResponseDate())
						.status(exception.getErrorCode())
						.error(exception.getMessage())
						.success(Boolean.FALSE)
							.build()
					);

	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex, WebRequest request) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					ErrorResponse.builder()
					.date(DateAndTimeUtils.getResponseDate())
					.error("Internal server error occurred.")
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.success(Boolean.FALSE)
					.build()
			);
	}



}
