//package example.springjwtgateway.security.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import example.springjwtgateway.security.domain.AuthenticatedUser;
//import example.springjwtgateway.security.dto.TokenExpirationResponse;
//import example.springjwtgateway.security.exception.TokenExpiredException;
//import example.springjwtgateway.security.util.JwtTokenUtil;
//import example.springjwtgateway.util.ObjectMapperConfiguration;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
//    private final ObjectMapper objectMapper = ObjectMapperConfiguration.getDefault();
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
//        super(authenticationManager);
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader(AuthConstants.AUTH_HEADER);
//
//        if (header == null || !header.startsWith(AuthConstants.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        final UsernamePasswordAuthenticationToken authentication;
//
//        try {
//            authentication = getAuthentication(request);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (TokenExpiredException e) {
//            response.getWriter().write(objectMapper.writeValueAsString(new TokenExpirationResponse(e.getLocalizedMessage())));
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws TokenExpiredException {
//        String token = request.getHeader(AuthConstants.AUTH_HEADER);
//
//        if (token != null) {
//            AuthenticatedUser user = AuthenticatedUser.fromToken(jwtTokenUtil, token);
//            if (user == null) {
//                return null;
//            } else {
//                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            }
//        } else {
//            return null;
//        }
//    }
//
//}
//
