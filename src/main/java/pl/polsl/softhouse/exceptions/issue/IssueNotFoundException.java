package pl.polsl.softhouse.exceptions.issue;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IssueNotFoundException extends HttpStatusCodeException {
    public IssueNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public IssueNotFoundException(Long id) {
        this("The issue with id " + id + " does not exists.");
    }
}
