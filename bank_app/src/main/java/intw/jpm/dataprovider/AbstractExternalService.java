package intw.jpm.dataprovider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import intw.jpm.properties.WebServiceProperties;
import reactor.core.publisher.Mono;

/**
 * AbstractResourceService
 *
 */
public abstract class AbstractExternalService {

	protected final WebClient webClient;
	protected final WebServiceProperties.Provider provider;
	protected final String appResourceBaseUrl;
	protected static Map<String,AbstractExternalService> externalServices = new HashMap<>();

	// For Services call with APIKey
	protected AbstractExternalService(WebClient.Builder webClientBuilder, WebServiceProperties webServiceProperties,
			String serviceProviderId) {
		this.provider = webServiceProperties.getDataproviders().get(serviceProviderId);
		this.appResourceBaseUrl = provider.getBaseUri();
		this.webClient = buildWebClient(webClientBuilder.clone(), provider);
	}

	public abstract Mono<ResponseEntity<Boolean>> call(String path, String query, HttpMethod method, HttpHeaders headers,
			String body);

	protected WebClient buildWebClient(WebClient.Builder webClientBuilder, WebServiceProperties.Provider provider) {
		webClientBuilder.baseUrl(provider.getBaseUri());
		return webClientBuilder.build();
	}

	protected URI getUri(String path, String query) {
		URI target;
		try {
			StringBuilder urlBuilder = new StringBuilder();
			if (path != null) {
				urlBuilder.append(path);
			}
			if (query != null) {
				urlBuilder.append('?');
				urlBuilder.append(query);
			}
			// Absolute uri is used as the query string is already url encoded
			target = new URI(appResourceBaseUrl + urlBuilder.toString());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
		return target;
	}

	public static Map<String,AbstractExternalService> getExternalServices(){
		return externalServices;
	}

}
