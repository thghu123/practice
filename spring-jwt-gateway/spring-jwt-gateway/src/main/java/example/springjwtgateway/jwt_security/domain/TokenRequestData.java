//package example.springjwtgateway.security.domain;
//
//import example.springjwtgateway.jpa.User.domain.Timezone;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Date;
//import java.util.Set;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class TokenRequestData {
//
//    private static final String JWT_ISSUER = "exem.com";
//
//    private final String subject;
//    private final String claimUserName;
//    private final Set<Role> claimRole;
//    private final Timezone claimTimezone;
//    private final String issuer;
//    private final Date issuedAt;
//
//    public static TokenRequestData from(AuthenticatedUser user) {
//        return TokenRequestData.builder()
//                .issuer(JWT_ISSUER)
//                .issuedAt(new Date())
//                .subject(user.getUsername())
//                .claimUserName(user.getUsername())
//                .claimRole(user.getRoles())
//                .claimTimezone(user.getTimezone())
//                .issuedAt(new Date())
//                .build();
//    }
//}
