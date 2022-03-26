package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends UserException {
    
    public UserAlreadyExistsException(String reason) {
        super(reason);
    }

    public static UserAlreadyExistsException fromUsername(String username) {
        return new UserAlreadyExistsException("User \"" + username + "\" already exist.");
    }
}
