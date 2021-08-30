package intw.jpm.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "intw.jpm.service")
public class WebServiceProperties {
	private final Map<String, Provider> dataproviders = new HashMap<>();

	public Map<String, Provider> getDataproviders() {
		return dataproviders;
	}


	public static class Provider {
		private String baseUri;
		private Integer connectionTimeout;

		public String getBaseUri() {
			return baseUri;
		}

		public void setBaseUri(String baseUri) {
			this.baseUri = baseUri;
		}

		public Integer getConnectionTimeout() {
			return connectionTimeout;
		}

		public void setConnectionTimeout(Integer connectionTimeout) {
			this.connectionTimeout = connectionTimeout;
		}

	}
}
