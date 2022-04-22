package pl.polsl.softhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class InvalidDataException extends HttpStatusCodeException {

    public InvalidDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
