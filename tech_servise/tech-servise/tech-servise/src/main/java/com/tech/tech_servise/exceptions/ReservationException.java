package com.tech.tech_servise.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ReservationException extends RuntimeException {
    private HttpStatus httpStatus;
    public ReservationException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
