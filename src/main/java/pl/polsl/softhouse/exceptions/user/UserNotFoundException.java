package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String reason) {
        super(reason);
    }

    public UserNotFoundException(long id) {
        super("User with ID \""+ id +"\" does not exist.");
    }

    public static UserNotFoundException fromUsername(String username) {
        return new UserNotFoundException("User \"" + username + "\" does not exist.");
    }
}
