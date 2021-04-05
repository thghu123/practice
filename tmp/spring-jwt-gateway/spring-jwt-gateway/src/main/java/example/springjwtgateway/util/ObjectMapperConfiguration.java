//package example.springjwtgateway.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//public class ObjectMapperConfiguration {
//
//    public static ObjectMapper getDefault() {
//        return new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//    }
//
//}
