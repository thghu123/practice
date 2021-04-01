//package example.pgConfig.rtm.slowquery.rts;
//
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.type.CollectionType;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//public class MessageParser {
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    public static byte[] toBytes(Object msg) {
//        try {
//            return OBJECT_MAPPER.writeValueAsBytes(msg);
//        } catch (JsonProcessingException var2) {
//            throw new RuntimeException(var2);
//        }
//    }
//
//    public static <T> T toObject(byte[] bytes, Class<T> cls) {
//        try {
//            return OBJECT_MAPPER.readValue(bytes, cls);
//        } catch (IOException var3) {
//            throw new RuntimeException(var3);
//        }
//    }
//
//    public static <T> List<T> toList(byte[] bytes, Class<T> cls) {
//        try {
//            CollectionType collectionType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, cls);
//            return (List)OBJECT_MAPPER.readValue(bytes, collectionType);
//        } catch (IOException var3) {
//            throw new RuntimeException(var3);
//        }
//    }
//
//    private MessageParser() {
//    }
//
//    static {
//        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDate.class, new MessageParser.LocalDateSerializer());
//        javaTimeModule.addDeserializer(LocalDate.class, new MessageParser.LocalDateDeserializer());
//        OBJECT_MAPPER.registerModule(javaTimeModule);
//    }
//
//    private static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
//        private LocalDateDeserializer() {
//        }
//
//        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//            return LocalDate.parse(p.getValueAsString(), MessageParser.FORMATTER);
//        }
//    }
//
//    private static class LocalDateSerializer extends JsonSerializer<LocalDate> {
//        private LocalDateSerializer() {
//        }
//
//        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//            gen.writeString(value.format(MessageParser.FORMATTER));
//        }
//    }
//}