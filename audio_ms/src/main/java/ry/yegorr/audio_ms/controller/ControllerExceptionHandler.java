package ry.yegorr.audio_ms.controller;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;
import ry.yegorr.audio_ms.exception.*;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleServerException(ApplicationException exception) {
        log.error(exception.getMessage(), exception);
        return sendResponse(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return handleServerException(new ApplicationException(exception.getMessage(), exception));
    }

    private ResponseEntity<?> sendResponse(HttpStatus httpStatus, String errorMessage) {
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
