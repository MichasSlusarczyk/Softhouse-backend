package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UserException {
    
    public UserNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public UserNotFoundException(long id) {
        this("User with ID \""+ id +"\" does not exist.");
    }

    public static UserNotFoundException fromUsername(String username) {
        return new UserNotFoundException("User \"" + username + "\" does not exist.");
    }
}
