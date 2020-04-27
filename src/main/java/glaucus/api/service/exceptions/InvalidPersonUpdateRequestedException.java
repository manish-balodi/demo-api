package glaucus.api.service.exceptions;

import org.springframework.validation.Errors;

public class InvalidPersonUpdateRequestedException extends PersonValidationException {

    public InvalidPersonUpdateRequestedException(Errors errors) {
        super("Invalid person update requested", errors);
    }

}
