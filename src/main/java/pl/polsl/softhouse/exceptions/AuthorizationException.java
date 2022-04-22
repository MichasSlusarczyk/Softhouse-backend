package pl.polsl.softhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class AuthorizationException extends HttpStatusCodeException {

    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
