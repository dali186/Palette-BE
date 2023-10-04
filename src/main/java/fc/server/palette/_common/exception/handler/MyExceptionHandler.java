package fc.server.palette._common.exception.handler;

import fc.server.palette._common.exception.*;
import fc.server.palette._common.exception.message.LogMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e, HttpServletRequest request) {
        printWarnLog(e, request);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e, HttpServletRequest request) {
        printWarnLog(e, request);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e, HttpServletRequest request) {
        printWarnLog(e, request);
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e, HttpServletRequest request) {
        printWarnLog(e, request);
        return new ResponseEntity<>(e.body(), e.status());
    }

    private <T extends Exception> void printWarnLog(T e, HttpServletRequest request) {
        LogMessage logMessage = getLogMessage(e);
        log.warn("[Url : {}], [Message : {}], [Cause Method : {}], [Cause Class Line : {}]\n",
                request.getRequestURI(), e.getMessage(), logMessage.getMethodName(), logMessage.getClassNameAndLine());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e, HttpServletRequest request) {
        LogMessage logMessage = getLogMessage(e);
        log.error("[Url : {}], [Message : {}], [Cause Method : {}], [Cause Class Line : {}]\n",
                request.getRequestURI(), e.getMessage(), logMessage.getMethodName(), logMessage.getClassNameAndLine());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverError(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        LogMessage logMessage = getLogMessage(e);
        log.error("[Url : {}], [Message : {}], [Exception : {}], [Cause Method : {}], [Cause Class Line : {}]\n",
                request.getRequestURI(), e.getMessage(), e, logMessage.getMethodName(), logMessage.getClassNameAndLine());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @MessageExceptionHandler(Exception.class)
    public void socketError(Exception e) {
        e.printStackTrace();
        LogMessage logMessage = getLogMessage(e);
        log.error("[Message : {}], [Exception : {}], [Cause Method : {}], [Cause Class Line : {}]\n",
                e.getMessage(), e, logMessage.getMethodName(), logMessage.getClassNameAndLine());
    }

    private LogMessage getLogMessage(Exception e) {
        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().startsWith("fc.server")) {
                return LogMessage.of(element);
            }
        }
        return LogMessage.of(e.getStackTrace()[0]);
    }
}
