package typecasters.tower_of_words.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<Object> response(HttpStatus status, String message, Object object) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", status);
        response.put("message", message);
        response.put("data", object);

        return new ResponseEntity<>(response, status);
    }
}
