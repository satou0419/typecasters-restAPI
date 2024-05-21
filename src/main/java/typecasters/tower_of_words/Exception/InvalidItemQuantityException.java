package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemQuantityException extends RuntimeException{
    public InvalidItemQuantityException(String message) {
        super(message);
    }
}
