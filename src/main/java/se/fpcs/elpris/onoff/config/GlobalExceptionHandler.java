package se.fpcs.elpris.onoff.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  //TODO handle org.springframework.security.authentication.LockedException (user account locked)



  // Catch-all exception handler
//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<ErrorResponse> handle(Exception ex, WebRequest request) {
//
//    log.error("{}: {}", ex.getClass().getName(), ex.getMessage(), ex);
//
//    return ResponseEntity
//        .status(HttpStatus.INTERNAL_SERVER_ERROR)
//        .body(ErrorResponse.builder() // do not leak internal state by returning error message
//            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//            .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//            .build());
//  }

}
