package pl.Komunikator.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Konfiguracja biblioteki Jackson do serializacji danych
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Customize the ObjectMapper configuration if needed
        return objectMapper;
    }
}