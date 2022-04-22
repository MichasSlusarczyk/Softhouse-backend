package pl.polsl.softhouse.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations()
                .forEach((violation) -> errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<String> handleHttpStatusCodeExceptions(HttpStatusCodeException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }
}
