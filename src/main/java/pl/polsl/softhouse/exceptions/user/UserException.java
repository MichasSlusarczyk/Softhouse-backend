package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserException extends HttpStatusCodeException {

    public UserException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public UserException(HttpStatus status, String message) {
        super(status, message);
    }
}
