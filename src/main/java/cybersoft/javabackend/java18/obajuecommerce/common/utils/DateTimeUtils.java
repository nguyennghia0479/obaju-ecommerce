package cybersoft.javabackend.java18.obajuecommerce.common.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static String getDateFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }
}
