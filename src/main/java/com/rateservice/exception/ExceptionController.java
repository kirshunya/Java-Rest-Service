package com.rateservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** JavaDoc COMMENT. */
@RestControllerAdvice
public class ExceptionController {
  /** JavaDoc COMMENT. */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Message> exception(Exception ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new Message(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<Message> requestMethod(HttpRequestMethodNotSupportedException ex) {
    return ResponseEntity.status(ex.getStatusCode())
        .body(new Message(ex.getStatusCode().toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({NoResourceFoundException.class})
  public ResponseEntity<Message> noResource(NoResourceFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new Message(ex.getStatusCode().toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Message> entityNotFound(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new Message(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Message> runtimeException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new Message(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  public ResponseEntity<Message> missingServlet(MissingServletRequestParameterException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new Message(HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({HttpServerErrorException.BadGateway.class})
  public ResponseEntity<Message> badGateway(HttpServerErrorException.BadGateway ex) {
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
        .body(new Message(HttpStatus.BAD_GATEWAY.toString(), ex.getMessage()));
  }

  /** JavaDoc COMMENT. */
  @ExceptionHandler({HttpClientErrorException.NotAcceptable.class})
  public ResponseEntity<Message> badGateway(HttpClientErrorException.NotAcceptable ex) {
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .body(new Message(HttpStatus.NOT_ACCEPTABLE.toString(), ex.getMessage()));
  }

  private record Message(String message, String description) {}
}
