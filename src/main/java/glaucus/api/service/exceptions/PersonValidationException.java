package glaucus.api.service.exceptions;

import org.springframework.validation.Errors;

import lombok.Data;

@Data
public class PersonValidationException extends Exception {

    String message;

    Errors errors;

    public PersonValidationException(String message, Errors errors) {
        super(message);
        this.errors = errors;

    }
}
