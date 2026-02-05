package com.nhannh.ecommerce.exception;

import com.nhannh.ecommerce.domain.dtos.ApiErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class EcommerceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(Exception e) {
        log.error("Unexpected error occurred", e);
        ApiErrorResponseDto error = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(getDetailsMessage(e))
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponseDto> handleBadCredentialsException(BadCredentialsException e) {
        log.error("The email/password is incorrect", e);
        ApiErrorResponseDto error = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(getDetailsMessage(e))
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("The input data is incorrect", e);
        ApiErrorResponseDto error = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(getDetailsMessage(e))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponseDto> handleNoSuchElementException(NoSuchElementException e) {
        log.error("The input data is incorrect", e);
        ApiErrorResponseDto error = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(getDetailsMessage(e))
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("The input data is incorrect", e);
        ApiErrorResponseDto error = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(getDetailsMessage(e))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String getDetailsMessage(Exception e) {
        if (Objects.nonNull(e.getCause())) {
            return e.getCause().getMessage();
        }
        return e.getMessage();
    }
}
