package pl.polsl.softhouse.exceptions.issue;

import pl.polsl.softhouse.exceptions.NotFoundException;

public class IssueNotFoundException extends NotFoundException {
    public IssueNotFoundException(String message) {
        super(message);
    }

    public IssueNotFoundException(long id) {
        super("Issue", id);
    }
}
