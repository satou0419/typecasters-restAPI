package typecasters.tower_of_words.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class NoDataResponse {
    public static ResponseEntity<Object> noDataResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();

        response.put("Status", status);
        response.put("Message", message);

        return new ResponseEntity<>(response, status);
    }
}
