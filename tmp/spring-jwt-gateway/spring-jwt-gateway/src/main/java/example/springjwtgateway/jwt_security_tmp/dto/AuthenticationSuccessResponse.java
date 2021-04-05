//package example.springjwtgateway.security.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import example.springjwtgateway.jpa.User.domain.Timezone;
//import example.springjwtgateway.security.domain.AuthenticatedUser;
//import example.springjwtgateway.security.domain.Role;
//import example.springjwtgateway.security.domain.Token;
//import example.springjwtgateway.util.DateTimeUtils;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Objects;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AuthenticationSuccessResponse {
//
//    private String token;
//
//    private String username;
//
//    @JsonFormat(pattern = DateTimeUtils.API_DATETIME_FORMATTER_PATTERN)
//    private LocalDateTime expiredDate;
//
//    private Set<Role> roles;
//
//    private Timezone timezone;
//
//    public static AuthenticationSuccessResponse from(AuthenticatedUser user, Token token) {
//        LocalDateTime expiredDate = token.getExpireAt()
//                    .toInstant()
//                    .atZone(getTimeZone(user))
//                .toLocalDateTime();
//
//        return new AuthenticationSuccessResponse(
//                token.getKey(),
//                user.getUsername(),
//                expiredDate,
//                user.getRoles(),
//                Objects.requireNonNullElse(user.getTimezone(), Timezone.UTC));
//    }
//
//    private static ZoneId getTimeZone(AuthenticatedUser user) {
//        return ZoneId.of(Objects.requireNonNullElse(user.getTimezone(), Timezone.UTC).name());
//    }
//
//}
