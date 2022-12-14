package com.im.port.config.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.im.port.config.exception.ErrorResponse;
import com.im.port.config.exception.OAuthProviderMissMatchException;
import com.im.port.config.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OAuthProviderMissMatchException.class)
    public ResponseEntity<ErrorResponse> handleOAuthProviderMissMatchException(OAuthProviderMissMatchException e){
        log.error(" @ handleOAuthProviderMissMatchException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
    
	//@Valid or @Validated 바인딩 에러시 발생 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(" @ handleMethodArgumentNotValidException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
    
    //@ModelAttribute로 바인딩 에러시에 발생
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e){
        log.error(" @ handleBindException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());
        
        return new ResponseEntity<>(errorResponse, status);
    }

	//enum 타입 불일치로 바인딩 에러시에 발생 (ex. @RequestParam enum에러)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.error(" @ handleMethodArgumentTypeMismatchException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
    
    //지원하지 않는 HTTP 메소드 호출시 발생
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(" @ handleHttpRequestMethodNotSupportedException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        log.error(" @ handleUserNotFoundException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
    
    //Authentication 권한이 없는 경우 발생(security에서 발생시킨다)
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error(" @ handleAccessDeniedException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
    
    @ExceptionHandler(IllegalAccessException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalAccessException(IllegalAccessException e){
        log.error(" @ handleIllegalAccessException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error(" @ handleException", e);
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.NOT_FOUND;
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
}