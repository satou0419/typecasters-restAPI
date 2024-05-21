package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException(String message) {
        super(message);
    }
}
