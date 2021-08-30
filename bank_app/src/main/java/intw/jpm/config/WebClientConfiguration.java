package intw.jpm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Expose a default WebClient.Builder
 *
 * @author Justin Tay
 *
 */
@Configuration
public class WebClientConfiguration {
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
