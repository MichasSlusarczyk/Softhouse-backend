package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends HttpStatusCodeException {
    
    public UserAlreadyExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public static UserAlreadyExistsException fromUsername(String username) {
        return new UserAlreadyExistsException("User \"" + username + "\" already exist.");
    }
}
