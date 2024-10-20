package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotInRoomException extends RuntimeException {
    public UserNotInRoomException(String message) {
        super(message);
    }
}
