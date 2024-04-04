package com.rateservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class.getName());

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exception(Exception ex) {
        logger.error("Error . Error not found.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error . Error not found.");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> requestMethod(HttpRequestMethodNotSupportedException ex) {
        logger.error("Error 405. Method not supported.", ex);
        return ResponseEntity.status(ex.getStatusCode()).body("Error 405. Method not supported.");
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> noResource(NoResourceFoundException ex) {
        logger.error("Error 404. Resource not found.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404. Resource not found.");
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException ex) {
        logger.error("Error 404. Entity not found.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404. Entity not found.");
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> runtimeException(RuntimeException ex) {
        logger.error("Error 500. Runtime exception.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 500. Runtime exception.");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> missingServlet(MissingServletRequestParameterException ex) {
        logger.error("Error 400. Missing Servlet.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 400. Missing Servlet.");
    }

    @ExceptionHandler({HttpServerErrorException.BadGateway.class})
    public ResponseEntity<Object> badGateway(HttpServerErrorException.BadGateway ex) {
        logger.error("Error 502. BadGeteway.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 502. BadGeteway.");
    }

    @ExceptionHandler({HttpClientErrorException.NotAcceptable.class})
    public ResponseEntity<Object> badGateway(HttpClientErrorException.NotAcceptable ex) {
        logger.error("Error 406. Not Acceptable.", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 406. Not Acceptable.");
    }
}
