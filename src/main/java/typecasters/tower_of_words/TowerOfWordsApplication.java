package typecasters.tower_of_words;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class TowerOfWordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowerOfWordsApplication.class, args);
	}

}
