package pl.polsl.softhouse.exceptions.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends HttpStatusCodeException {

    public RequestNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public RequestNotFoundException(long id) {
        this("Request with id \"" + id + "\" does not exist.");
    }
}
