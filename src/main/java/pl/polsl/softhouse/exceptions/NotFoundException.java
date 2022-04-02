package pl.polsl.softhouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends HttpStatusCodeException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String entityName, long id) {
        this(entityName + " with ID \""+ id +"\" does not exist.");
    }
}
