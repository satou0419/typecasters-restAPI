package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApiSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(ApiSchedulerService.class);

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ApiSchedulerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 600000) // 10 minutes
    public void fetchAllItems() {
        try {
            // Make a GET request to the API
            String response = restTemplate.getForObject(apiUrl, String.class);

            // Handle the API response as needed
            logger.info("API Response: {}", response);
            System.out.println("Calling API");
        } catch (Exception e) {
            logger.error("Error occurred while fetching items: {}", e.getMessage(), e);
        }
    }
}
