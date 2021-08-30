package intw.jpm.dataprovider.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import intw.jpm.dataprovider.AbstractExternalService;
import intw.jpm.dataprovider.ServiceProviderId;
import intw.jpm.exception.ErrorResponse;
import intw.jpm.exception.ExternalServiceException;
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
public class AppExternalDataSourceService1 extends AbstractExternalService {

	@Autowired
	public AppExternalDataSourceService1(WebClient.Builder webClientBuilder,
			WebServiceProperties webServiceProperties) {
		super(webClientBuilder, webServiceProperties, ServiceProviderId.APP_DATASOURCE_1);
		externalServices.put(ServiceProviderId.APP_DATASOURCE_1, this);
	}

	@Retry(name = ServiceProviderId.APP_DATASOURCE_1)
	@CircuitBreaker(name = ServiceProviderId.APP_DATASOURCE_1, fallbackMethod = "noServiceAvailableFallback")
	@TimeLimiter(name = ServiceProviderId.APP_DATASOURCE_1)
	public Mono<ResponseEntity<Boolean>> call(String path, String query, HttpMethod method, HttpHeaders headers,
			String body) {

		return webClient.method(method).uri(getUri(path, query)).accept(headers.getAccept().toArray(MediaType[]::new))
				.contentType(headers.getContentType() == null ? MediaType.ALL : headers.getContentType())
				.contentLength(headers.getContentLength()).bodyValue((body == null ? "" : body)).retrieve()
				.toEntity(Boolean.class);
	}



	public Mono<ErrorResponse> noServiceAvailableFallback(Exception ex) {
	   throw new ExternalServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}



}
