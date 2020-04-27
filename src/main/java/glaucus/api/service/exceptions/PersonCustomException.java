package glaucus.api.service.exceptions;

import lombok.Data;

@Data
public class PersonCustomException extends Exception {

    private String message;

    private int code;

    public PersonCustomException(String message) {
        super(message);
    }

    public PersonCustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
