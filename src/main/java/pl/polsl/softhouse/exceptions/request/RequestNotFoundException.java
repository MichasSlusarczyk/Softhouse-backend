package pl.polsl.softhouse.exceptions.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import pl.polsl.softhouse.exceptions.NotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends NotFoundException {

    public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException(long id) {
        this("Request with id \"" + id + "\" does not exist.");
    }
}
