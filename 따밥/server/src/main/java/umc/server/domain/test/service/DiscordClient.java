package umc.server.domain.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordClient {

    private final RestClient restClient;

    public void send(String webhookUrl, DiscordEmbedMessage message) {
        URI uri = URI.create(webhookUrl);
        try{
            ResponseEntity<String> response = restClient.post()
                    .uri(uri)
                    .body(message)
                    .retrieve()
                    .toEntity(String.class);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
