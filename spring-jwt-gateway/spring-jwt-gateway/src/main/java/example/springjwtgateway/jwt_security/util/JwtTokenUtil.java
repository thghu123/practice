//package example.springjwtgateway.security.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import example.springjwtgateway.jpa.User.domain.Timezone;
//import example.springjwtgateway.security.config.AuthConstants;
//import example.springjwtgateway.security.domain.Role;
//import example.springjwtgateway.security.domain.Token;
//import example.springjwtgateway.security.domain.TokenRequestData;
//import example.springjwtgateway.security.exception.TokenExpiredException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.text.DateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static java.lang.String.format;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtTokenUtil {
//
//    private static final String JWT_SECRET = "ThisIsASecretKeyForJwtExample"; // TODO change secret
//    private static final String CLAIM_USERNAME_KEY = "username";
//    private static final String CLAIM_ROLE_KEY = "role";
//    private static final String CLAIM_TIMEZONE_KEY = "timezone";
//
//    public Token generateToken(TokenRequestData request) {
//        Objects.requireNonNull(request);
//
//        final Date expiredDate = createDefaultExpiredDate();
//
//        String tokenKey = JWT.create()
//                .withSubject(request.getSubject())
//                .withExpiresAt(expiredDate)
//                .withClaim(CLAIM_USERNAME_KEY, request.getClaimUserName())
//                .withClaim(CLAIM_ROLE_KEY, (Date) getRolesAsString(request.getClaimRole()))
//                .withClaim(CLAIM_TIMEZONE_KEY, Objects.requireNonNullElse(request.getClaimTimezone(), Timezone.UTC).name())
//                .withIssuer(request.getIssuer())
//                .withIssuedAt(request.getIssuedAt())
//                .sign(Algorithm.HMAC256(JWT_SECRET.getBytes()));
//
//        return Token.from(tokenKey, request, expiredDate);
//    }
//
//    public List<String> getRolesAsString(Set<Role> roles) {
//        return roles.stream()
//                .map(Role::name)
//                .collect(Collectors.toList());
//    }
//
//    protected Date createExpiredDateForOneWeek() {
//        return new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
//    }
//
//    protected Date createDefaultExpiredDate() {
//        return createExpiredDateForOneWeek();
//    }
//
//    public static Date getExpiredDate(String token) {
//        return JWT.require(Algorithm.HMAC256(JWT_SECRET.getBytes()))
//                .build()
//                .verify(token)
//                .getExpiresAt();
//    }
//
//    private static String convertLocale(Date date) {
//        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
//        return dateFormat.format(date);
//    }
//
//    public DecodedJWT decodeJwtToken(String bearerToken) throws TokenExpiredException {
//        final String token = bearerToken.replace(AuthConstants.TOKEN_PREFIX, "").trim();
//        final Date expiredAt = JWT.decode(token).getExpiresAt();
//        if (expiredAt.before(new Date())) {
//            throw new TokenExpiredException(format("token expired at %s", convertLocale(expiredAt)));
//        }
//        return JWT.require(Algorithm.HMAC256(JWT_SECRET.getBytes()))
//                .build()
//                .verify(token);
//    }
//
//    public String getTimezone(DecodedJWT decodedJWT) {
//        return decodedJWT.getClaim(CLAIM_TIMEZONE_KEY).asString();
//    }
//
//    public Set<Role> getRoles(DecodedJWT decodedJWT) {
//        return Arrays.stream(decodedJWT.getClaim(CLAIM_ROLE_KEY)
//                .asArray(String.class))
//                .map(Role::valueOf).collect(Collectors.toSet());
//    }
//
//}
