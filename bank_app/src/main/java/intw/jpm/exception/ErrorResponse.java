package intw.jpm.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	/**
	 *
	 */
	private static final long serialVersionUID = -1681325554420203522L;

	private String date;

	private Integer status;

	private String error;

	private String message;

	private Boolean success;

	 private List<ValidationError> errors;

	  @Getter
	  @Setter
	  @RequiredArgsConstructor
	  private static class ValidationError {
	    private final String field;
	    private final String message;
	  }

	  public void addValidationError(String field, String message){
	    if(Objects.isNull(errors)){
	      errors = new ArrayList<>();
	    }
	    errors.add(new ValidationError(field, message));
	  }

}

