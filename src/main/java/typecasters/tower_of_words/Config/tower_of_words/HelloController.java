package typecasters.tower_of_words.Config.tower_of_words;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "OTIN SUPREMACY!!!!";
    }
}