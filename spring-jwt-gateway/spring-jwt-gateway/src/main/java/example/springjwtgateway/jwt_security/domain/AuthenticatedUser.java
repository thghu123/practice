//package example.springjwtgateway.security.domain;
//
//import com.auth0.jwt.interfaces.DecodedJWT;
//import example.springjwtgateway.jpa.User.domain.Timezone;
//import example.springjwtgateway.jpa.User.domain.User;
//import example.springjwtgateway.security.exception.TokenExpiredException;
//import example.springjwtgateway.security.util.JwtTokenUtil;
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Data
//public class AuthenticatedUser implements UserDetails {
//
//    private String username;
//
//    private String password;
//
//    private Timezone timezone;
//
//    private Set<Role> roles;
//
//
//    public static UserDetails from(User user) {
//        return AuthenticatedUser.builder()
//                .username(user.getName())
//                .password(user.getPassword())
//                .roles(Set.of(Role.USER)) // TODO Role
//                .timezone(user.getTimeZone())
//                .build();
//    }
//
//    @Builder
//    public AuthenticatedUser(String username, String password,
//                             Timezone timezone, Set<Role> roles) {
//        this.username = username;
//        this.password = password;
//        this.timezone = timezone;
//        this.roles = roles;
//    }
//
//    public static AuthenticatedUser fromToken(JwtTokenUtil jwtTokenUtil, String token) throws TokenExpiredException, TokenExpiredException {
//        final DecodedJWT decodedJWT = jwtTokenUtil.decodeJwtToken(token);
//        return AuthenticatedUser.builder()
//                .username(decodedJWT.getSubject())
//                .timezone(Timezone.valueOf(jwtTokenUtil.getTimezone(decodedJWT)))
//                .roles(jwtTokenUtil.getRoles(decodedJWT))
//                .build();
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(r -> new SimpleGrantedAuthority(r.getRole()))
//                .collect(Collectors.toUnmodifiableSet());
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
