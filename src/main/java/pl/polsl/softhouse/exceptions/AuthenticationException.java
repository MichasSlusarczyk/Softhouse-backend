package pl.polsl.softhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class AuthenticationException extends HttpStatusCodeException {

    public AuthenticationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
