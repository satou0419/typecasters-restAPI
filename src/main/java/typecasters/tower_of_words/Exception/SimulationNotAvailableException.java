package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class SimulationNotAvailableException extends RuntimeException {
    public SimulationNotAvailableException(String message) {
        super(message);
    }
}
