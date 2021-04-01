package example.springjwtgateway.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeUtils {
    public static final String DEFAULT_TIMEZONE = "UTC";
    public static final String API_DATETIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter API_DATETIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern(API_DATETIME_FORMATTER_PATTERN)
                .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();

}
