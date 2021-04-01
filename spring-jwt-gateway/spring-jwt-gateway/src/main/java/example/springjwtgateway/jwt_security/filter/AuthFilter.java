//package example.springjwtgateway.security.filter;
//
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import example.springjwtgateway.security.domain.AuthenticatedUser;
//        import example.springjwtgateway.security.domain.Token;
//        import example.springjwtgateway.security.domain.TokenRequestData;
//        import example.springjwtgateway.security.dto.AuthenticationFailureResponse;
//        import example.springjwtgateway.security.dto.AuthenticationSuccessResponse;
//        import example.springjwtgateway.security.dto.UserLoginRequest;
//        import example.springjwtgateway.security.util.JwtTokenUtil;
//        import example.springjwtgateway.util.ObjectMapperConfiguration;
//        import org.springframework.security.authentication.AuthenticationManager;
//        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//        import org.springframework.security.core.Authentication;
//        import org.springframework.security.core.AuthenticationException;
//        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//        import javax.servlet.FilterChain;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import java.io.IOException;
//
//public class AuthFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final ObjectMapper objectMapper = ObjectMapperConfiguration.getDefault();
//    private final JwtTokenUtil jwtTokenUtil;
//
//    private final AuthenticationManager authenticationManager;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.authenticationManager = authenticationManager;
//        setFilterProcessesUrl(AuthConstants.LOGIN_ENDPOINT);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        final UserLoginRequest userRequest;
//
//        try {
//            userRequest = objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        userRequest.getUsername(),
//                        userRequest.getPassword()));
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException {
//        response.getWriter().write(
//                objectMapper.writeValueAsString(
//                        AuthenticationFailureResponse.from(failed.getLocalizedMessage())));
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//        response.getWriter().flush();
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication auth) throws IOException {
//
//        AuthenticatedUser user = (AuthenticatedUser) auth.getPrincipal();
//
//        Token token = jwtTokenUtil.generateToken(TokenRequestData.from(user));
//
//        response.getWriter()
//                .write(objectMapper.writeValueAsString(
//                        AuthenticationSuccessResponse.from(user, token)));
//
//        response.getWriter().flush();
//    }
//}
