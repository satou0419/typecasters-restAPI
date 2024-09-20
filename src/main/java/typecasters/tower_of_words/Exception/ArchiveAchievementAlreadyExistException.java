package typecasters.tower_of_words.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArchiveAchievementAlreadyExistException extends RuntimeException {
    public ArchiveAchievementAlreadyExistException(String message) {
        super(message);
    }
}
