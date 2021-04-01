//package example.pgConfig.rtm.slowquery.rts;
//
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//
//public class XmTimeUtils {
//    private static final ZoneId TIMEZONE = ZoneId.of("UTC");
//    private static final ZoneOffset TIMEZONE_OFFSET;
//
//    public static LocalDateTime now() {
//        return LocalDateTime.now(TIMEZONE);
//    }
//
//    public static LocalDateTime toLocalDateTime(long epochMillis) {
//        return Instant.ofEpochMilli(epochMillis).atZone(TIMEZONE).toLocalDateTime();
//    }
//
//    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
//        return timestamp == null ? null : timestamp.toInstant().atZone(TIMEZONE).toLocalDateTime();
//    }
//
//    public static long toEpochMillis(LocalDateTime localDateTime) {
//        return localDateTime.toInstant(TIMEZONE_OFFSET).toEpochMilli();
//    }
//
//    public static Instant toInstant(LocalDateTime localDateTime) {
//        return localDateTime == null ? null : localDateTime.toInstant(TIMEZONE_OFFSET);
//    }
//
//    private XmTimeUtils() {
//    }
//
//    static {
//        TIMEZONE_OFFSET = ZoneOffset.UTC;
//    }
//}
//
