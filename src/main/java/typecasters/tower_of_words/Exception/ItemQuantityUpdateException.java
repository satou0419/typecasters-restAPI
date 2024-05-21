package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemQuantityUpdateException extends RuntimeException {
    public ItemQuantityUpdateException(String message) {
        super(message);
    }

    public ItemQuantityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}