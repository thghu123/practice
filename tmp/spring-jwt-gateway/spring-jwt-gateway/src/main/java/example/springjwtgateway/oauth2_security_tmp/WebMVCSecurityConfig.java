//package example.springjwtgateway.oauth2_config_tmp;
//
//import example.springjwtgateway.accounts.AccountService;
//import example.springjwtgateway.security.filter.AuthFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;
//import org.springframework.security.web.server.savedrequest.ServerRequestCache;
//import org.springframework.web.server.WebFilter;
//
//@Configuration
////@EnableWebSecurity
////@EnableAuthorizationServer
//@RequiredArgsConstructor
//public class WebMVCSecurityConfig extends WebSecurityConfigurerAdapter{
//    //상속 이후부터 기본 Spring Security 설정 적용되지 않음.
//
//    private final AccountService accountService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    TokenStore tokenStore(){
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService)
//                .passwordEncoder(passwordEncoder);
//    } //maneger의 옵션을 부여해 구현
//
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().mvcMatchers("/docs/index.html");
//        web.ignoring().mvcMatchers("/actuator");
//    } //시큐리티 필터를 적용할지 말지를 설정
//
//
////    Resource Config 에서 설정할 부분 제거
///*
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .anonymous() //익명 사용자 허용
//                    .and()
//                .formLogin() //로그인 폼 화면 인증 허용
//                    .and()
//                .authorizeRequests()
//                        .mvcMatchers(HttpMethod.GET, "/api/**").anonymous()
//                        .anyRequest().authenticated();
//                //기본적인 인증을 할 경우에는 접근이 가능하다.
//    }
//*/
//
//
//
//}
