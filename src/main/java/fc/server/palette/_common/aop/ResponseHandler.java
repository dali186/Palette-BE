package fc.server.palette._common.aop;

import fc.server.palette._common.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ResponseHandler {
    @Pointcut("execution(* fc.server.palette..*Controller.*(..))")
    public void paletteController() {
    }

    @Around("paletteController()")
    public Object validationAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object proceed = jp.proceed();

        if (!(proceed instanceof ResponseEntity<?>)) {
            return proceed;
        }

        ResponseEntity<?> originalReturnValue = (ResponseEntity<?>) proceed;
        Object body = originalReturnValue.getBody();
        HttpStatus statusCode = originalReturnValue.getStatusCode();
        return ResponseEntity.status(statusCode).body(ApiUtils.success(statusCode, body));
    }
}
