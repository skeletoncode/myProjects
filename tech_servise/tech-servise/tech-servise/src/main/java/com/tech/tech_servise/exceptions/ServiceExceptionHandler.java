package com.tech.tech_servise.exceptions;

import com.tech.tech_servise.dto.ServiceErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handle (ServiceException ex, WebRequest request) {
        ServiceErrorDTO errorDTO = new ServiceErrorDTO(ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), ex.getHttpStatus(), request);
    }

}
