package intw.jpm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimeUtils {

	private static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	private static final String RESPONSE_DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	private static final String UI_DATE_FORMAT = "dd/MM/yyyy";
	private static final String UI_TIME_FORMAT = "HH:mm";
	private static final String CHIME_DATE_ID_FORMAT = "ddMMyyyyHHmmss";
	private static final String HHMMSS_DATE_FORMAT = "HH:mm:ss";

	private DateAndTimeUtils() {

	}

	public static String getResponseDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(RESPONSE_DATETIME_FORMAT));
	}

	public static String getChimeIdFormatDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CHIME_DATE_ID_FORMAT));
	}


	public static String getUIStringDate(LocalDate localDate) {
		return localDate != null ? localDate.format(DateTimeFormatter.ofPattern(UI_DATE_FORMAT)) : "";
	}

	public static String getUIStringTime(LocalTime localTime) {
		return localTime != null ? localTime.format(DateTimeFormatter.ofPattern(UI_TIME_FORMAT)) : "";
	}

	public static LocalDate getDBLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE_FORMAT);
		return date != null ? LocalDate.parse(String.valueOf(date), formatter) : null;
	}

	public static LocalDate getUILocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UI_DATE_FORMAT);
		return date != null ? LocalDate.parse(String.valueOf(date), formatter) : null;
	}

	public static LocalTime getLocalTimeFromString(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HHMMSS_DATE_FORMAT);
		return time != null ? LocalTime.parse(String.valueOf(time), formatter) : null;
	}

	public static int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return date != null ? dayOfWeek : null;
	}

	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
}
