package com.dharmendra.CineLock.common;

import com.dharmendra.CineLock.common.exception.DuplicateResourceException;
import com.dharmendra.CineLock.common.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.time.Instant;
import java.util.stream.Collectors;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. Catch 404: When a resource is not found
    @ExceptionHandler(RelationServiceNotRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse>handleDuplicateResources(
            DuplicateResourceException ex,
            HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(
                Instant.now(),
                HttpStatus.CONTINUE.value(),
                "DUPLICATE_RESOURCE",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    } // 3. Catch 400: When @Valid fails on request bodies
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){
        String combinedErrors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ErrorResponse errorResponse=new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_FAILED",
                combinedErrors,
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    // 4. Catch 500: Fallback for any unhandled unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception ex,
            HttpServletRequest request) {
        log.error("Unhandled exception occurred on path {}: ", request.getRequestURI(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Please try again later.",
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


