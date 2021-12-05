package ru.yegorr.ms.controller;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;
import ru.yegorr.ms.exception.*;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<?> handleServerException(ServerException exception) {
        log.error(exception.getMessage(), exception);
        return sendResponse(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<?> handleClientException(ClientException exception) {
        return sendResponse(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return handleServerException(new ServerException(exception));
    }

    private ResponseEntity<?> sendResponse(HttpStatus httpStatus, String errorMessage) {
        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
