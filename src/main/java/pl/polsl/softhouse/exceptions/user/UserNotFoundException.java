package pl.polsl.softhouse.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import pl.polsl.softhouse.exceptions.NotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NotFoundException {
    
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(long id) {
        super("User", id);
    }

    public static UserNotFoundException fromUsername(String username) {
        return new UserNotFoundException("User \"" + username + "\" does not exist.");
    }
}
