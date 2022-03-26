package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserException extends HttpStatusCodeException {

    public UserException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public UserException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
