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
//public class Token {
//
//    private final String key;
//    private final String subject;
//    private final String claimUserName;
//    private final Set<Role> claimRole;
//    private final Timezone claimTimezone;
//    private final String issuer;
//    private final Date issuedAt;
//    private final Date expireAt;
//
//    public static Token from(String tokenKey, TokenRequestData request, Date expiredDate) {
//        return Token.builder()
//                .key(tokenKey)
//                .subject(request.getSubject())
//                .claimUserName(request.getClaimUserName())
//                .claimRole(request.getClaimRole())
//                .claimTimezone(request.getClaimTimezone())
//                .issuer(request.getIssuer())
//                .issuedAt(request.getIssuedAt())
//                .expireAt(expiredDate)
//                .build();
//    }
//}
