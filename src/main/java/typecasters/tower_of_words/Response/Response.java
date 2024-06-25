package typecasters.tower_of_words.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<Object> response(HttpStatus status, String message, Object object) {
        Map<String, Object> response = new HashMap<>();

        response.put("Status", status);
        response.put("Message", message);
        response.put("Data", object);

        return new ResponseEntity<>(response, status);
    }
}
