package fc.server.palette._common.exception.handler;

import fc.server.palette._common.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e) {
        printWarnLog(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e) {
        printWarnLog(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e) {
        printWarnLog(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e) {
        printWarnLog(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e) {
        e.printStackTrace();
        log.error("Message : {}, Method : {}", e.getMessage(), e.getStackTrace()[0].getMethodName());
        return new ResponseEntity<>(e.body(), e.status());
    }

    private static <T extends Exception> void printWarnLog(T e) {
        log.warn("Message : {}, Method : {}", e.getMessage(), e.getStackTrace()[0].getMethodName());
    }
}
