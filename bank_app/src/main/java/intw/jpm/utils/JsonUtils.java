package intw.jpm.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private JsonUtils() {
		// to hide implicit public constructor
	}

	public static String toJSON(Object object) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsString(object);
	}

	public static <T> T toObject(String jsonStr,Class<T> clazz) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonStr, clazz);
	}
}