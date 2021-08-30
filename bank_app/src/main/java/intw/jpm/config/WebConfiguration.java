package intw.jpm.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * WebConfig
 *
 * @author Justin Tay
 *
 */
@Configuration
public class WebConfiguration {
	/**
	 * Customise the jackson object mapper
	 * <p>
	 * The other way to customise the object mapper is via the spring.jackson
	 * property in application.yml however timeZone needs to be set here if the
	 * resources are not OffsetDateTime otherwise they will be with respect to UTC
	 *
	 * @param timeZone
	 * @return
	 */
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(TimeZone timeZone) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.timeZone(timeZone); // Sets the timezone to local instead of UTC
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
				DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return builder;
	}
}
