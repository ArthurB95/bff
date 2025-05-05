package com.example.fake_api_us.infrastructure.exception.handle;

import com.example.fake_api_us.infrastructure.exception.BusinessException;
import com.example.fake_api_us.infrastructure.exception.ConflictException;
import com.example.fake_api_us.infrastructure.exception.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntityException(UnprocessableEntityException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(ConflictException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data) {
         return ResponseEntity
                 .status(status)
                 .body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));
     }
}
