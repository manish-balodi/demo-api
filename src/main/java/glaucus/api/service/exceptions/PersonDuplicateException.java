package glaucus.api.service.exceptions;

import org.springframework.validation.Errors;

import lombok.Data;

@Data
public class PersonDuplicateException extends PersonValidationException {

    public PersonDuplicateException(Errors errors) {
        super("Person is duplicate", errors);
    }
}
