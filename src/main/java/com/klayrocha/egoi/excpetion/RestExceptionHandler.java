package com.klayrocha.egoi.excpetion;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.klayrocha.egoi.controller.CategoryController;

/**
 * Class error handling
 * 
 * @author Francis Klay Rocha
 *
 */
@ControllerAdvice(assignableTypes = CategoryController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = Logger.getLogger(RestExceptionHandler.class.getName());

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.log(Level.SEVERE, "E-goi ERROR : " + ex.getMessage(), ex.getMessage());
		String error = "Internal Error, please contact the system administrator";
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
