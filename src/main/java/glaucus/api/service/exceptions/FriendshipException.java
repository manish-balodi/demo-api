package glaucus.api.service.exceptions;

import lombok.Data;

@Data
public class FriendshipException extends Exception {

    private String message;

    private int code;

    public FriendshipException(String message) {
        super(message);
    }

    public FriendshipException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
