//package example.springjwtgateway.security.config;
//
//import example.springjwtgateway.security.CustomUserDetailsService;
//import example.springjwtgateway.security.domain.Role;
//import example.springjwtgateway.security.util.JwtTokenUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@ConditionalOnMissingBean(NoneSecurityConfig.class)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService userDetailsService;
//    private final JwtTokenUtil jwtTokenUtil;
//
//    @PostConstruct
//    public void setup() {
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//        web.ignoring().requestMatchers()
//                .antMatchers("/graphiql")
//                .antMatchers("/docs/index.html");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().disable().csrf().disable();
//
//        // Set session management to stateless
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        // Set unauthorized requests exception handler
//        http.exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            log.error("Unauthorized request - {}", ex.getMessage());
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//                        });
//
//        // Set permissions on endpoints
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST,AuthConstants.SINGUP_ENDPOINT).permitAll()
//
//                // public endpoints
//                .antMatchers("/api/public/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/public/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/public/**").permitAll()
//                // admin endpoints
//                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
//                .antMatchers("/actuator/**").hasRole(Role.ADMIN.name())
//                // private endpoints
//                .anyRequest().authenticated();
//
//        // Add JWT token filter
//        http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenUtil));
//
//        http.formLogin();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        final CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
//
//}
