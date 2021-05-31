package com.urbancompany.commonservice.exception;

import static com.urbancompany.commonservice.Constants.FAILURE;

import java.util.LinkedList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.commonservice.response.ErrorResponse;
import com.urbancompany.commonservice.response.ValidationError;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<BaseResponse> handleBusinessException(BusinessException exp) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse(FAILURE, exp.getMessage()));
	}

	@ExceptionHandler(TechnicalException.class)
	public final ResponseEntity<BaseResponse> handleTechnicalException(TechnicalException exp) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new BaseResponse(FAILURE, exp.getMessage()));
	}

	@ExceptionHandler(AuthenticationError.class)
	public final ResponseEntity<BaseResponse> handleAuthenticationError(AuthenticationError exp) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new BaseResponse(FAILURE, "user authentication failed"));
	}

	@ExceptionHandler(AuthorizationError.class)
	public final ResponseEntity<BaseResponse> handleAuthorizationError(AuthorizationError exp) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new BaseResponse(FAILURE, "user not authorized to access this resourse"));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exp) {
		List<ValidationError> errors = new LinkedList<>();
		exp.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(new ValidationError(fieldName, errorMessage));
		});
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(FAILURE, "payload validation error", errors));
	}

}
