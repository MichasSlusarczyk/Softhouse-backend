package pl.polsl.softhouse.components;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GenericValidator<T> {

    private final Validator validator;

    public GenericValidator(Validator validator) {
        this.validator = validator;
    }

    public void validateOrThrow(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Set<ConstraintViolation<T>> validate(T entity) {
        return validator.validate(entity);
    }

    public Validator getValidator() {
        return validator;
    }
}
