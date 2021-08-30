package intw.jpm.dataprovider.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import intw.jpm.dataprovider.AbstractExternalService;
import intw.jpm.dataprovider.ServiceProviderId;
import intw.jpm.properties.WebServiceProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import reactor.core.publisher.Mono;

/**
 * AppQueuServiceResourceService
 *
 */
@Service
public class AppExternalDataSourceService2 extends AbstractExternalService {

	@Autowired
	public AppExternalDataSourceService2(WebClient.Builder webClientBuilder,
			WebServiceProperties webServiceProperties) {
		super(webClientBuilder, webServiceProperties, ServiceProviderId.APP_DATASOURCE_2);
		externalServices.put(ServiceProviderId.APP_DATASOURCE_2, this);
	}

	@Retry(name = ServiceProviderId.APP_DATASOURCE_2)
	@CircuitBreaker(name = ServiceProviderId.APP_DATASOURCE_2)
	@TimeLimiter(name = ServiceProviderId.APP_DATASOURCE_2)
	public Mono<ResponseEntity<Boolean>> call(String path, String query, HttpMethod method, HttpHeaders headers,
			String body) {

		return webClient.method(method).uri(getUri(path, query)).accept(headers.getAccept().toArray(MediaType[]::new))
				.contentType(headers.getContentType() == null ? MediaType.ALL : headers.getContentType())
				.contentLength(headers.getContentLength()).bodyValue((body == null ? "" : body)).retrieve()
				.toEntity(Boolean.class);
	}

}
