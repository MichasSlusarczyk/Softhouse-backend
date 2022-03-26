package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String reason) {
        super(reason);
    }
}
