package com.codesuttlle.EmployeeDetails.advices;

import com.codesuttlle.EmployeeDetails.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        APIError apiError=APIError.builder().httpStatus(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return  buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> internalServerError(Exception exception){
        APIError apiError=APIError.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<APIResponse<?>> buildErrorResponseEntity(APIError apiError) {
        return new ResponseEntity<>(new APIResponse<>(apiError),apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> methodArgunmentNotValidException(MethodArgumentNotValidException exception){
        List<String> errorList=exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        APIError apiError=APIError.builder().httpStatus(HttpStatus.BAD_REQUEST).message("Input validation failed").build();
        return  buildErrorResponseEntity(apiError);
    }
}
