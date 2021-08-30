package intw.jpm.config;

import java.time.Clock;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Time Configuration
 *
 * @author Justin Tay
 *
 */
@Configuration
public class TimeConfiguration {

	@Bean
	public TimeZone timeZone() {
		return TimeZone.getDefault();
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}
